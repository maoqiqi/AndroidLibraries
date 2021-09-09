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
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-01 21:01
 * @version v1.0.0
 */
open class LogLifecycleObserver : LifecycleObserver {

    protected var logInfo = LogUtils.LogInfo(javaClass.simpleName)

    init {
        LogUtils.v(logInfo, "${javaClass.simpleName} created:", this.toString())
    }

    // 每次回调都会调用onAny
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onAny() {
        LogUtils.v(logInfo, "onAny()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        LogUtils.v(logInfo, "onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        LogUtils.v(logInfo, "onStart()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        LogUtils.v(logInfo, "onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        LogUtils.v(logInfo, "onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        LogUtils.v(logInfo, "onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        LogUtils.v(logInfo, "onDestroy()")
    }
}