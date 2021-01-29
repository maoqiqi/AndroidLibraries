package com.codearms.maoqiqi.log

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 生命周期观察者
 * author: March
 * date: 2020-11-01 21:01
 * version v1.0.0
 */
class LogLifecycleObserver : LifecycleObserver {

    // 每次回调都会调用onAny
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        LogUtils.v("onAny()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        LogUtils.v("onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        LogUtils.v("onStart()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        LogUtils.v("onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        LogUtils.v("onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtils.v("onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        LogUtils.v("onDestroy()")
    }
}