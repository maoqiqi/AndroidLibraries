package com.codearms.maoqiqi.androidlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codearms.maoqiqi.androidlibraries.LogUtils.debugLog
import com.codearms.maoqiqi.androidlibraries.LogUtils.errorLog
import com.codearms.maoqiqi.androidlibraries.LogUtils.infoLog
import com.codearms.maoqiqi.androidlibraries.LogUtils.verboseLog
import com.codearms.maoqiqi.androidlibraries.LogUtils.warnLog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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