package com.codearms.maoqiqi.log

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Log application activity lifecycle callbacks
 * author: March
 * date: 2021-02-23 21:01
 * version v1.0.0
 */
class LogLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        LogUtils.v("onActivityCreated(activity: Activity, savedInstanceState: Bundle?)")
    }

    override fun onActivityStarted(activity: Activity) {
        LogUtils.v("onActivityStarted(activity: Activity)")
    }

    override fun onActivityResumed(activity: Activity) {
        LogUtils.v("onActivityResumed(activity: Activity)")
    }

    override fun onActivityPaused(activity: Activity) {
        LogUtils.v("onActivityPaused(activity: Activity)")
    }

    override fun onActivityStopped(activity: Activity) {
        LogUtils.v("onActivityStopped(activity: Activity)")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LogUtils.v("onActivitySaveInstanceState(activity: Activity, outState: Bundle)")
    }

    override fun onActivityDestroyed(activity: Activity) {
        LogUtils.v("onActivityDestroyed(activity: Activity)")
    }
}