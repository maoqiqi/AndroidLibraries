/*
 * Copyright [2021] [March]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("RequestExtensions")

package com.codearms.maoqiqi.viewmodel

import com.codearms.maoqiqi.log.LogUtils
import kotlinx.coroutines.*
import retrofit2.Call
import kotlin.system.measureTimeMillis

/**
 * Request
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-04-20 21:01
 * @version v1.0.0
 */
private val logInfo = LogUtils.LogInfo("RequestExtensions").apply { isLine = true }

/**
 * Launch a request
 * @param coroutineScope The context of this scope
 * @param jobId Job id
 * @param delayTime Delay time
 * @param success Success callback
 * @param block Request block
 */
private fun <T> launchRequest(
    coroutineScope: CoroutineScope,
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<T?>) -> Unit,
    block: suspend CoroutineScope.() -> T?,
): Job = coroutineScope.launch(Dispatchers.IO) {
    try {
        if (delayTime != 0L) delay(delayTime)
        val elapsedTime = measureTimeMillis {
            val resp: T? = block(this)
            coroutineScope.launch(Dispatchers.Main) { success(Result.Success(resp)) }
        }
        LogUtils.e(logInfo, "elapsedTime:$elapsedTime")
    } catch (e: Exception) {
        e.printStackTrace()
        coroutineScope.launch(Dispatchers.Main) { success(Result.Error(jobId, e)) }
    }
}

/**
 * Launch batch requests
 * @param coroutineScope The context of this scope
 * @param jobId Job id
 * @param delayTime Delay time
 * @param success Success callback
 * @param block Request block list
 */
private fun <T> launchBatchRequest(
    coroutineScope: CoroutineScope,
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<List<T?>>) -> Unit,
    block: suspend CoroutineScope.() -> List<Deferred<T?>>,
): Job = coroutineScope.launch(Dispatchers.IO) {
    try {
        if (delayTime != 0L) delay(delayTime)
        val elapsedTime = measureTimeMillis {
            val apis: List<Deferred<T?>> = block()
            coroutineScope.launch(Dispatchers.Main) { success(Result.Success(List(apis.size) { i -> apis[i].await() })) }
        }
        LogUtils.e(logInfo, "elapsedTime:$elapsedTime")
    } catch (e: Exception) {
        e.printStackTrace()
        coroutineScope.launch(Dispatchers.Main) { success(Result.Error(jobId, e)) }
    }
}

private fun <T : ResponseResultData<R?>, R> getResultData(data: T?): R? =
    data?.let { if (it.isSuccess()) it.getResponseResultData() else throw ApiException(it.getResponseErrorCode(), it.getResponseErrorMsg(), it) }
        ?: throw ResultDataNullException("ResultData was null but resp type was declared as non-null")

private fun <T : ResponseResultData<*>> getBatchResultData(data: T?): Any? =
    data?.let { if (it.isSuccess()) it.getResponseResultData() else throw ApiException(it.getResponseErrorCode(), it.getResponseErrorMsg(), it) }
        ?: throw ResultDataNullException("ResultData was null but resp type was declared as non-null")

private fun <T : ResponseResultData<R?>, R> handlerData(jobId: String?, resp: Result<T?>): Result<R?> = when (resp) {
    is Result.Error -> resp
    is Result.Success -> try {
        Result.Success(getResultData(resp.data))
    } catch (e: Exception) {
        Result.Error(jobId, e)
    }
}

private fun <T : ResponseResultData<*>> handlerBatchResult(jobId: String?, resp: Result<List<T?>>): Result<List<Any?>> = when (resp) {
    is Result.Error -> resp
    is Result.Success -> try {
        resp.data?.let { data -> Result.Success(data.map { getBatchResultData(it) }) }
            ?: Result.Error(jobId, ResultDataNullException("ResultData was null but resp type was declared as non-null"))
    } catch (e: Exception) {
        Result.Error(jobId, e)
    }
}

/**
 * Launch a request
 * @param jobId Job id
 * @param delayTime Delay time
 * @param block Request block
 * @param success Success callback
 */
fun <T> CoroutineScope.request(
    jobId: String? = null,
    delayTime: Long = 0,
    block: suspend () -> T?,
    success: (Result<T?>) -> Unit,
): Job = launchRequest(this, jobId, delayTime, success) { block() }

/**
 * Launch a request
 * @param jobId Job id
 * @param delayTime Delay time
 * @param block Request block
 * @param success Success callback
 */
fun <T : ResponseResultData<R?>, R> CoroutineScope.requestResultData(
    jobId: String? = null,
    delayTime: Long = 0,
    block: suspend () -> T?,
    success: (Result<R?>) -> Unit,
): Job = launchRequest(this, jobId, delayTime, { success(handlerData(jobId, it)) }) { block() }

/**
 * Launch batch requests
 * @param jobId Job id
 * @param delayTime Delay time
 * @param success Success callback
 * @param block Request block list
 */
fun CoroutineScope.batchRequest(
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<List<Any?>>) -> Unit,
    vararg block: suspend () -> Any?,
): Job = launchBatchRequest(this, jobId, delayTime, success) { List(block.size) { i -> async { block[i]() } } }

/**
 * Launch batch requests
 * @param jobId Job id
 * @param delayTime Delay time
 * @param success Success callback
 * @param block Request block list
 */
fun <T : ResponseResultData<*>> CoroutineScope.batchRequestResultData(
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<List<Any?>>) -> Unit,
    vararg block: suspend () -> T,
): Job = launchBatchRequest(this, jobId, delayTime, { success(handlerBatchResult(jobId, it)) }) { List(block.size) { i -> async { block[i]() } } }

@ExperimentalCoroutinesApi
fun <T> CoroutineScope.callRequest(
    jobId: String? = null,
    delayTime: Long = 0,
    block: suspend () -> Call<T?>,
    success: (Result<T?>) -> Unit,
): Job = launchRequest(this, jobId, delayTime, success) { block().await() }

@ExperimentalCoroutinesApi
fun <T : ResponseResultData<R?>, R> CoroutineScope.callRequestResultData(
    jobId: String? = null,
    delayTime: Long = 0,
    block: suspend () -> Call<T>,
    success: (Result<R?>) -> Unit,
): Job = launchRequest(this, jobId, delayTime, success) { block().awaitResultData() }

@ExperimentalCoroutinesApi
fun CoroutineScope.callBatchRequest(
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<List<Any?>>) -> Unit,
    vararg block: suspend () -> Call<Any?>,
): Job = launchBatchRequest(this, jobId, delayTime, success) { List(block.size) { i -> async { block[i]().await() } } }

@ExperimentalCoroutinesApi
fun <T : ResponseResultData<*>> CoroutineScope.callBatchRequestResultData(
    jobId: String? = null,
    delayTime: Long = 0,
    success: (Result<List<Any?>>) -> Unit,
    vararg block: suspend () -> Call<T>,
): Job = launchBatchRequest(this, jobId, delayTime, success) { List(block.size) { i -> async { block[i]().awaitResult() } } }