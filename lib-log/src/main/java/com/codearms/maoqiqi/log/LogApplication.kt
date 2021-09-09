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

import android.app.Application
import android.content.res.Configuration

/**
 * Log application
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-01 21:01
 * @version v1.0.0
 */
class LogApplication : Application() {

    protected var logInfo = LogUtils.LogInfo(javaClass.simpleName)

    init {
        LogUtils.v(logInfo, "${javaClass.simpleName} created:", this.toString())
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.v(logInfo, "-->onCreate()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.v(logInfo, "-->onConfigurationChanged(newConfig: Configuration)")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        LogUtils.v(logInfo, "-->onLowMemory()")
    }

    // TRIM_MEMORY_COMPLETE:内存不足,并且该进程在后台进程列表最后一个,马上就要被清理
    // TRIM_MEMORY_MODERATE:内存不足,并且该进程在后台进程列表的中部
    // TRIM_MEMORY_BACKGROUND:内存不足,并且该进程是后台进程
    // TRIM_MEMORY_UI_HIDDEN:内存不足,并且该进程的UI已经不可见了
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        LogUtils.v(logInfo, "-->onTrimMemory(level: Int)")
    }
}