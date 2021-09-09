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

@file:JvmName("RetrofitCallExtensions")

package com.codearms.maoqiqi.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import kotlin.coroutines.resumeWithException

/**
 * Retrofit Call Extensions
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-04-20 21:01
 * @version v1.0.0
 */

fun <T : Any> Call<T>.noDataException(): KotlinNullPointerException {
    val invocation = this.request().tag(Invocation::class.java)!!
    val method = invocation.method()
    return KotlinNullPointerException("Response from ${method.declaringClass.name}.${method.name} was null but response body type was declared as non-null")
}

@ExperimentalCoroutinesApi
suspend fun <T : ResponseResultData<R?>, R> Call<T>.awaitResultData(): R? {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess()) continuation.resume(it.getResponseResultData(), null) else {
                            val apiException = ApiException(it.getResponseErrorCode(), it.getResponseErrorMsg(), it.getResponseResultData())
                            continuation.resumeWithException(apiException)
                        }
                    } ?: continuation.resumeWithException(noDataException())
                } else {
                    continuation.resumeWithException(HttpException(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

@ExperimentalCoroutinesApi
suspend fun <T : ResponseResultData<*>> Call<T>.awaitResult(): Any? {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isSuccess()) continuation.resume(it.getResponseResultData(), null) else {
                            val apiException = ApiException(it.getResponseErrorCode(), it.getResponseErrorMsg(), it.getResponseResultData())
                            continuation.resumeWithException(apiException)
                        }
                    } ?: continuation.resumeWithException(noDataException())
                } else {
                    continuation.resumeWithException(HttpException(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

@ExperimentalCoroutinesApi
suspend fun <T : Any> Call<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { continuation.resume(it, null) } ?: continuation.resumeWithException(noDataException())
                } else {
                    continuation.resumeWithException(HttpException(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

@ExperimentalCoroutinesApi
@JvmName("awaitNullable")
suspend fun <T : Any> Call<T?>.await(): T? {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                if (response.isSuccessful) {
                    continuation.resume(response.body(), null)
                } else {
                    continuation.resumeWithException(HttpException(response))
                }
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

@ExperimentalCoroutinesApi
suspend fun <T> Call<T>.awaitResponse(): Response<T> {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resume(response, null)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}