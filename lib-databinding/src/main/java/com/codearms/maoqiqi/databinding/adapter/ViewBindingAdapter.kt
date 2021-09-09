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

package com.codearms.maoqiqi.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * BindingAdapters created for easier implementation for View DataBinding
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-03-23 21:01
 * @version v1.0.0
 */
@BindingAdapter("goneUnless")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("hiddenUnless")
fun View.setHidden(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("paddingLeft")
fun View.setPaddingLeft(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(newValue, paddingTop, paddingRight, paddingBottom)
}

@BindingAdapter("paddingRight")
fun View.setPaddingRight(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(paddingLeft, paddingTop, newValue, paddingBottom)
}

@BindingAdapter("paddingTop")
fun View.setPaddingTop(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(paddingLeft, newValue, paddingRight, paddingBottom)
}

@BindingAdapter("paddingBottom")
fun View.setPaddingBottom(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(paddingLeft, paddingTop, paddingRight, newValue)
}

@BindingAdapter("paddingHorizontal")
fun View.setPaddingHorizontal(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(newValue, paddingTop, newValue, paddingBottom)
}

@BindingAdapter("paddingVertical")
fun View.setPaddingVertical(oldValue: Int, newValue: Int) {
    if (oldValue != newValue) setPadding(paddingLeft, newValue, paddingRight, newValue)
}

@BindingAdapter("onLayoutChange")
fun View.setOnLayoutChangeListener(oldValue: View.OnLayoutChangeListener?, newValue: View.OnLayoutChangeListener?) {
    oldValue?.let { removeOnLayoutChangeListener(it) }
    newValue?.let { addOnLayoutChangeListener(it) }
}