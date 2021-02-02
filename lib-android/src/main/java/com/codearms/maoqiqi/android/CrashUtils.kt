package com.codearms.maoqiqi.android

import android.os.Looper
import android.os.Process
import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer
import kotlin.system.exitProcess

object CrashUtils : Thread.UncaughtExceptionHandler {

    // 获取系统默认的UncaughtException处理器
    private val defaultHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    init {
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    // 当UncaughtException发生时会转入该函数来处理
    override fun uncaughtException(t: Thread, e: Throwable) {
        if (defaultHandler != null && !handleException(e)) {
            defaultHandler.uncaughtException(t, e)
        } else {
            Thread.sleep(3000)
            // 退出程序
            Process.killProcess(Process.myPid())
            exitProcess(0)
        }
    }

    /**
     * 自定义错误处理,收集错误信息,发送错误报告等操作均在此完成
     * @param e Throwable
     * @return true:处理了该异常信息,否则返回false
     */
    private fun handleException(e: Throwable): Boolean {
        // 使用Toast来显示异常信息
        Thread {
            Looper.prepare()
            // ToastUtils.show("很抱歉,程序出现异常,即将退出!");
            Looper.loop()
        }.start()

        // 收集设备参数信息
        val sb = StringBuilder()
        val map: Map<String, String> = com.codearms.maoqiqi.android.DeviceUtils.getDeviceInfo()
        for ((key, value) in map) {
            sb.append(key)
            sb.append("=")
            sb.append(value)
            sb.append("\n")
        }
        val writer: Writer = StringWriter()
        val printWriter = PrintWriter(writer)
        e.printStackTrace(printWriter)
        var cause = e.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        sb.append(writer.toString())
        writer.close()
        Log.e("mfq", sb.toString())

        // 保存错误信息到文件中
//        val path: String = application.getPackageName() + "/crash"
//        val fileName: String = FileUtils.saveLogToFile(path, sb.toString())
//        return fileName != null
        return true
    }
}