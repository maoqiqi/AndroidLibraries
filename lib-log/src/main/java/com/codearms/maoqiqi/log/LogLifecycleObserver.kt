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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Log lifecycle observer
 * author: March
 * date: 2021-02-01 21:01
 * version v1.0.0
 */
class LogLifecycleObserver : LifecycleObserver {

    protected var logInfo: LogUtils.LogInfo? = LogUtils.LogInfo(javaClass.simpleName)

    // 每次回调都会调用onAny
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {
        LogUtils.v(logInfo, "onAny()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        LogUtils.v(logInfo, "onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        LogUtils.v(logInfo, "onStart()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        LogUtils.v(logInfo, "onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        LogUtils.v(logInfo, "onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtils.v(logInfo, "onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        LogUtils.v(logInfo, "onDestroy()")
    }
}