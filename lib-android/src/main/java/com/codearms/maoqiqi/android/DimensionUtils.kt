package com.codearms.maoqiqi.android

import android.content.Context
import androidx.annotation.DimenRes

// returns dip(dp) dimension value in pixels
fun Context.dp2px(value: Int) = (value * resources.displayMetrics.density).toInt()
fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()

// return sp dimension value in pixels
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

// converts px value into dip
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2dp(px: Float): Float = px / resources.displayMetrics.density

// converts px value into sp
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity
fun Context.px2sp(px: Float): Float = px / resources.displayMetrics.scaledDensity

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)