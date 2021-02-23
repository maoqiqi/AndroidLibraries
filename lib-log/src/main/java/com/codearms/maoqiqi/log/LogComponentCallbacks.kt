package com.codearms.maoqiqi.log

import android.content.ComponentCallbacks
import android.content.res.Configuration

/**
 * Log component callbacks
 * author: March
 * date: 2021-02-23 21:01
 * version v1.0.0
 */
class LogComponentCallbacks : ComponentCallbacks {

    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtils.v("onConfigurationChanged(newConfig: Configuration)")
    }

    override fun onLowMemory() {
        LogUtils.v("onLowMemory()")
    }
}