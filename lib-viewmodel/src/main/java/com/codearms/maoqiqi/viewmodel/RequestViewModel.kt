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

package com.codearms.maoqiqi.viewmodel

import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codearms.maoqiqi.log.LogUtils
import com.codearms.maoqiqi.log.LogViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.net.SocketTimeoutException

/**
 * Request ViewModel
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-04-20 21:01
 * @version v1.0.0
 */
abstract class RequestViewModel : LogViewModel() {

    val jobs: ArrayMap<String, Job> = ArrayMap()
    val showLoading: MutableLiveData<String> = MutableLiveData()
    val loadingInfo: MutableLiveData<String?> = MutableLiveData()
    val hiddenLoading: MutableLiveData<String> = MutableLiveData()

    protected fun addJob(isShowLoading: Boolean = false, loadingMessage: String? = null, request: (String) -> Job): String {
        LogUtils.v(logInfo, "Start time", System.currentTimeMillis())
        val jobId = getJobId()
        if (isShowLoading) {
            showLoading.value = jobId
            loadingInfo.value = loadingMessage
        }
        jobs[jobId] = request(jobId)
        return jobId
    }

    protected fun <T> handlerResult(jobId: String, result: Result<T?>, liveData: MutableLiveData<T?>) {
        LogUtils.v(logInfo, "End time", System.currentTimeMillis())
        hiddenLoading.value = jobId
        when (result) {
            is Result.Success -> liveData.value = result.data
            is Result.Error -> onError(jobId, result.e)
        }
    }

    protected fun <T> CoroutineScope.addJobForRequest(
        result: MutableLiveData<T?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        block: suspend () -> T?,
    ): String = addJob(isShowLoading, loadingMessage) { jobId -> request(jobId, delayTime, block) { handlerResult(jobId, it, result) } }

    protected fun <T> addJobForRequest(
        result: MutableLiveData<T?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        block: suspend () -> T?,
    ): String = viewModelScope.addJobForRequest(result, isShowLoading, loadingMessage, delayTime, block)

    protected fun <T : ResponseResultData<R?>, R> CoroutineScope.addJobForRequestResultData(
        result: MutableLiveData<R?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        block: suspend () -> T?,
    ): String = addJob(isShowLoading, loadingMessage) { jobId -> requestResultData(jobId, delayTime, block) { handlerResult(jobId, it, result) } }

    protected fun <T : ResponseResultData<R?>, R> addJobForRequestResultData(
        result: MutableLiveData<R?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        block: suspend () -> T?,
    ): String = viewModelScope.addJobForRequestResultData(result, isShowLoading, loadingMessage, delayTime, block)

    protected fun CoroutineScope.addJobForBatchRequest(
        result: MutableLiveData<List<Any?>?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        vararg block: suspend () -> Any?,
    ): String = addJob(isShowLoading, loadingMessage) { jobId -> batchRequest(jobId, delayTime, { handlerResult(jobId, it, result) }, *block) }

    protected fun addJobForBatchRequest(
        result: MutableLiveData<List<Any?>?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        vararg block: suspend () -> Any?,
    ): String = viewModelScope.addJobForBatchRequest(result, isShowLoading, loadingMessage, delayTime, *block)

    protected fun <T : ResponseResultData<*>> CoroutineScope.addJobForBatchRequestResultData(
        result: MutableLiveData<List<Any?>?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        vararg block: suspend () -> T,
    ): String = addJob(isShowLoading, loadingMessage) { jobId -> batchRequestResultData(jobId, delayTime, { handlerResult(jobId, it, result) }, *block) }

    protected fun <T : ResponseResultData<*>> addJobForBatchRequestResultData(
        result: MutableLiveData<List<Any?>?>,
        isShowLoading: Boolean = false,
        loadingMessage: String? = null,
        delayTime: Long = 0,
        vararg block: suspend () -> T,
    ): String = viewModelScope.addJobForBatchRequestResultData(result, isShowLoading, loadingMessage, delayTime, *block)

    open fun getJobId() = "job_" + System.currentTimeMillis()

    fun getJob(jobId: String): Job? {
        return jobs[jobId]
    }

    fun cancel(key: String) {
        getJob(key)?.cancel()
    }

    fun cancelAll() {
        viewModelScope.cancel()
    }

    open fun onError(jobId: String?, e: Exception) {
        hiddenLoading.value = jobId
        LogUtils.v(logInfo, "jobId", jobId, "e", e)
        when (e) {
            is HttpException -> LogUtils.v(logInfo, e.message)
            is ResultDataNullException -> LogUtils.v(logInfo, e.message)
            is ApiException -> LogUtils.v(logInfo, e.message)
            is CancellationException -> LogUtils.v(logInfo, e.message)
            is SocketTimeoutException -> LogUtils.e(logInfo, e.message)
            is FileNotFoundException -> LogUtils.e(logInfo, e.message)
        }
    }
}