package com.codearms.maoqiqi.log

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codearms.maoqiqi.log.LogUtils.debugLog
import com.codearms.maoqiqi.log.LogUtils.errorLog
import com.codearms.maoqiqi.log.LogUtils.infoLog
import com.codearms.maoqiqi.log.LogUtils.verboseLog
import com.codearms.maoqiqi.log.LogUtils.warnLog
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogUtilsTestKotlin {

    @Test
    fun testLog() {
        "123456".verboseLog()
        "123456".debugLog()
        "123456".infoLog()
        "123456".warnLog()
        "123456".errorLog()
        LogUtils.v("123456")
        LogUtils.d("123456")
        LogUtils.i("123456")
        LogUtils.w("123456")
        LogUtils.e("123456")
        LogUtils.v("1", "2", "3", "4", "5", "6")
        LogUtils.d("1", "2", "3", "4", "5", "6")
        LogUtils.i("1", "2", "3", "4", "5", "6")
        LogUtils.w("1", "2", "3", "4", "5", "6")
        LogUtils.e("1", "2", "3", "4", "5", "6")
    }
}