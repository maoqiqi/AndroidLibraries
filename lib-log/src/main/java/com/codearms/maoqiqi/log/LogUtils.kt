package com.codearms.maoqiqi.log

import android.util.Log

/**
 * Log utils
 * author: March
 * date: 2021-02-01 21:01
 * version v1.0.0
 */
object LogUtils {

    data class LogInfo @JvmOverloads constructor(
        val tag: String,
        val isDebug: Boolean = true,
        val isLine: Boolean = true
    )

    // Global log control switch
    @JvmStatic
    var isDebug = true

    @JvmStatic
    fun v(vararg msg: Any?) {
        if (!isDebug) return
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val logInfo = if (hasLog) msg[0] as LogInfo else LogInfo(defaultTagName())
        if (logInfo.isDebug) Log.v(logInfo.tag, getMsg(hasLog, logInfo.isLine, *msg))
    }

    @JvmStatic
    fun d(vararg msg: Any?) {
        if (!isDebug) return
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val logInfo = if (hasLog) msg[0] as LogInfo else LogInfo(defaultTagName())
        if (logInfo.isDebug) Log.d(logInfo.tag, getMsg(hasLog, logInfo.isLine, *msg))
    }

    @JvmStatic
    fun i(vararg msg: Any?) {
        if (!isDebug) return
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val logInfo = if (hasLog) msg[0] as LogInfo else LogInfo(defaultTagName())
        if (logInfo.isDebug) Log.i(logInfo.tag, getMsg(hasLog, logInfo.isLine, *msg))
    }

    @JvmStatic
    fun w(vararg msg: Any?) {
        if (!isDebug) return
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val logInfo = if (hasLog) msg[0] as LogInfo else LogInfo(defaultTagName())
        if (logInfo.isDebug) Log.w(logInfo.tag, getMsg(hasLog, logInfo.isLine, *msg))
    }

    @JvmStatic
    fun e(vararg msg: Any?) {
        if (!isDebug) return
        val hasLog = msg.isNotEmpty() && msg[0] is LogInfo
        val logInfo = if (hasLog) msg[0] as LogInfo else LogInfo(defaultTagName())
        if (logInfo.isDebug) Log.e(logInfo.tag, getMsg(hasLog, logInfo.isLine, *msg))
    }

    private fun defaultTagName(): String {
        val stackTrace = Thread.currentThread().stackTrace
        var className: String
        for (i in 2 until stackTrace.size) {
            className = stackTrace[i].className
            if (className != javaClass.name) {
                return className.substring(className.lastIndexOf(".") + 1)
            }
        }
        return "TAG"
    }

    private fun getMsg(hasLog: Boolean, isLine: Boolean, vararg msg: Any?): String {
        val indices: IntRange = (if (hasLog) 1 else 0) until msg.size
        if (isLine) {
            val stackTrace = Thread.currentThread().stackTrace
            var element: StackTraceElement
            for (i in 2 until stackTrace.size) {
                element = stackTrace[i]
                if (element.className != javaClass.name) {
                    return getLineInfo(element) + msg.sliceArray(indices).joinToString()
                }
            }
        }
        return msg.sliceArray(indices).joinToString()
    }

    private fun getLineInfo(element: StackTraceElement): String {
        return "┋┋thread:${Thread.currentThread().name}[${Thread.currentThread().id}](${element.fileName}:${element.lineNumber})┋┋ "
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
}