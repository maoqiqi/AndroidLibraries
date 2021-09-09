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

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Log application activity lifecycle callbacks
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-23 21:01
 * @version v1.0.0
 */
open class LogLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    protected var logInfo = LogUtils.LogInfo(javaClass.simpleName)

    init {
        LogUtils.v(logInfo, "${javaClass.simpleName} created:", this.toString())
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        LogUtils.v(logInfo, "onActivityCreated(activity: Activity, savedInstanceState: Bundle?)")
    }

    override fun onActivityStarted(activity: Activity) {
        LogUtils.v(logInfo, "onActivityStarted(activity: Activity)")
    }

    override fun onActivityResumed(activity: Activity) {
        LogUtils.v(logInfo, "onActivityResumed(activity: Activity)")
    }

    override fun onActivityPaused(activity: Activity) {
        LogUtils.v(logInfo, "onActivityPaused(activity: Activity)")
    }

    override fun onActivityStopped(activity: Activity) {
        LogUtils.v(logInfo, "onActivityStopped(activity: Activity)")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LogUtils.v(logInfo, "onActivitySaveInstanceState(activity: Activity, outState: Bundle)")
    }

    override fun onActivityDestroyed(activity: Activity) {
        LogUtils.v(logInfo, "onActivityDestroyed(activity: Activity)")
    }
}