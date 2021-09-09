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

/**
 * Response data processing results
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-04-20 21:01
 * @version v1.0.0
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val jobId: String?, val e: Exception) : Result<Nothing>()

    override fun toString(): String = when (this) {
        is Success -> "Success"
        is Error -> "Error"
    }
}