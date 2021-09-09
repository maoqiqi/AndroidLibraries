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

package com.codearms.maoqiqi.log

import android.util.Log

/**
 * Utils about log
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-01 21:01
 * @version v1.0.0
 */
object LogUtils {

    data class LogInfo @JvmOverloads constructor(var tagName: String? = null, var isDebug: Boolean = true) {

        var isLine: Boolean = false
            set(value) {
                field = value
                lineInfo = if (isLine) getLineInfo(getElement()) else ""
            }
        var lineInfo: String

        init {
            val element = getElement()
            tagName = tagName ?: getTagName(element)
            lineInfo = if (isLine) getLineInfo(element) else ""
        }

        fun getTag(): String = tagName ?: "TAG"

        override fun toString(): String {
            return "LogInfo(tagName=$tagName, isDebug=$isDebug, isLine=$isLine, lineInfo='$lineInfo')"
        }
    }

    // Global log control switch
    @JvmStatic
    var isDebug = true

    private fun getLogInfo(vararg msg: Any?): LogUtils.LogInfo {
        return (if (msg.isNotEmpty() && msg[0] is LogUtils.LogInfo) msg[0] as LogUtils.LogInfo else LogUtils.LogInfo())
    }

    @JvmStatic
    fun v(vararg msg: Any?) {
        if (isDebug) with(getLogInfo(*msg)) { if (isDebug) Log.v(getTag(), getMsg(lineInfo, *msg)) }
    }

    @JvmStatic
    fun d(vararg msg: Any?) {
        if (isDebug) with(getLogInfo(*msg)) { if (isDebug) Log.d(getTag(), getMsg(lineInfo, *msg)) }
    }

    @JvmStatic
    fun i(vararg msg: Any?) {
        if (isDebug) with(getLogInfo(*msg)) { if (isDebug) Log.i(getTag(), getMsg(lineInfo, *msg)) }
    }

    @JvmStatic
    fun w(vararg msg: Any?) {
        if (isDebug) with(getLogInfo(*msg)) { if (isDebug) Log.w(getTag(), getMsg(lineInfo, *msg)) }
    }

    @JvmStatic
    fun e(vararg msg: Any?) {
        if (isDebug) with(getLogInfo(*msg)) { if (isDebug) Log.e(getTag(), getMsg(lineInfo, *msg)) }
    }

    @JvmStatic
    fun printStackTrace(logInfo: LogInfo? = null, error: Exception? = null) {
        with(logInfo ?: getLogInfo()) {
            Log.e(getTag(), getMsg(lineInfo, error ?: "** No detailed error message **"))
            // Print our stack trace
            val trace = error?.stackTrace ?: Thread.currentThread().stackTrace
            for (traceElement in trace) Log.e(getTag(), getMsg(lineInfo, "\tat $traceElement"))
            // Print cause, if any
            error?.cause?.let { Log.e(getTag(), getMsg(lineInfo, it.message)) }
        }
    }

    private fun getElement(): StackTraceElement? {
        Thread.currentThread().stackTrace.forEachIndexed { index, element -> if (index >= 2 && checkClassName(element)) return element }
        return null
    }

    private fun checkClassName(element: StackTraceElement): Boolean = if (element.className.indexOf("$") == -1) {
        element.className != javaClass.name
    } else {
        element.className.substring(0, element.className.indexOf("$")) != javaClass.name
    }

    private fun getTagName(element: StackTraceElement?, defaultTag: String? = null): String {
        var className: String = element?.className ?: return defaultTag ?: "TAG"
        className = className.substring(className.lastIndexOf(".") + 1)
        val index = className.indexOf("$")
        if (index != -1) className = className.substring(0, index)
        return className
    }

    private fun getClassName(className: String): String {
        val index = className.lastIndexOf('.')
        return className.substring(if (index == -1) 0 else index + 1, className.length)
    }

    private fun getLineInfo(element: StackTraceElement?): String {
        val info: String = element?.let { "(${it.fileName}:${it.lineNumber})" } ?: ""
        return "┋┋thread:${Thread.currentThread().name}[${Thread.currentThread().id}]$info┋┋ "
    }

    private fun getMsg(lineInfo: String, vararg msg: Any?): String {
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val indices: IntRange = (if (hasLog) 1 else 0) until msg.size
        return lineInfo + msg.sliceArray(indices).joinToString()
    }

    fun String?.verboseLog() = v(this)

    fun String?.debugLog() = d(this)

    fun String?.infoLog() = i(this)

    fun String?.warnLog() = w(this)

    fun String?.errorLog() = e(this)

    fun <T> Array<T>.verboseLog() = v(this)

    fun <T> Array<T>.debugLog() = d(this)

    fun <T> Array<T>.infoLog() = i(this)

    fun <T> Array<T>.warnLog() = w(this)

    fun <T> Array<T>.errorLog() = e(this)

    fun <T> List<T>.verboseLog() = v(this)

    fun <T> List<T>.debugLog() = d(this)

    fun <T> List<T>.infoLog() = i(this)

    fun <T> List<T>.warnLog() = w(this)

    fun <T> List<T>.errorLog() = e(this)
}