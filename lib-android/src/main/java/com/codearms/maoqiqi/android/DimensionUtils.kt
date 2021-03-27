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

import android.content.Context
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

// returns dip(dp) dimension value in pixels
fun Context.dp2px(value: Int) = (value * resources.displayMetrics.density).toInt()
fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()

fun Fragment.dp2px(value: Int) = requireActivity().dp2px(value)
fun Fragment.dp2px(value: Float): Int = requireActivity().dp2px(value)

// return sp dimension value in pixels
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Fragment.sp2px(value: Int): Int = requireActivity().sp2px(value)
fun Fragment.sp2px(value: Float): Int = requireActivity().sp2px(value)

// converts px value into dip
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2dp(px: Float): Float = px / resources.displayMetrics.density

fun Fragment.px2dp(px: Int): Float = requireActivity().px2dp(px)
fun Fragment.px2dp(px: Float): Float = requireActivity().px2dp(px)

// converts px value into sp
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity
fun Context.px2sp(px: Float): Float = px / resources.displayMetrics.scaledDensity

fun Fragment.px2sp(px: Int): Float = px2sp(px)
fun Fragment.px2sp(px: Float): Float = px2sp(px)

fun Context.dimen(@DimenRes resId: Int): Int = resources.getDimensionPixelSize(resId)
fun Fragment.dimen(@DimenRes resId: Int): Int = requireActivity().dimen(resId)