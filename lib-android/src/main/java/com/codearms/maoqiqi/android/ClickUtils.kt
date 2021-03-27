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

package com.codearms.maoqiqi.android

import android.util.Log
import android.view.View

object ClickUtils {

    // 添加一个上次触发时间的属性
    var <T : View>T.lastClickTime: Long
        get() = getTag(R.id.last_click_time) as? Long ?: 0
        set(value) = setTag(R.id.last_click_time, value)

    @JvmStatic
    @JvmOverloads
    fun <T : View> T.singleClick(block: (T) -> Unit, delay: Long = 800) {
        setOnClickListener {
            val currentTimeMillis = System.currentTimeMillis()
            if (currentTimeMillis - lastClickTime > delay) {
                Log.v("ClickUtils", lastClickTime.toString())
                lastClickTime = currentTimeMillis
                Log.v("ClickUtils", currentTimeMillis.toString())
                block(this)
            }
        }
    }

    // 兼容点击事件设置为this的情况
    @JvmStatic
    @JvmOverloads
    fun <T : View> T.singleClick(listener: View.OnClickListener, delay: Long = 800) {
        singleClick({ listener.onClick(this) }, delay)
    }
}