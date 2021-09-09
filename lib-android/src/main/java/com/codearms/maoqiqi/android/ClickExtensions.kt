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

@file:JvmName("ClickExtensions")

package com.codearms.maoqiqi.android

import android.util.Log
import android.view.View

/**
 * 给View添加一个上次触发时间的属性
 */
private var <T : View>T.lastClickTime: Long
    get() = getTag(R.id.last_click_time) as? Long ?: 0
    set(value) = setTag(R.id.last_click_time, value)

/**
 * 带延迟过滤点击事件的View扩展
 */
@JvmOverloads
fun <T : View> T.singleClick(delay: Long = 800, block: ((T) -> Unit)?) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        Log.e("ClickExtensions", "currentTimeMillis:$currentTimeMillis")
        if (currentTimeMillis - lastClickTime > delay) {
            Log.e("ClickExtensions", "lastClickTime:$lastClickTime")
            lastClickTime = currentTimeMillis
            block?.invoke(this)
        }
    }
}

/**
 * 兼容点击事件设置为this或者OnClickListener对象的情况
 */
@JvmOverloads
fun <T : View> T.singleClick(delay: Long = 800, listener: View.OnClickListener?) {
    singleClick(delay) { listener?.onClick(this) }
}