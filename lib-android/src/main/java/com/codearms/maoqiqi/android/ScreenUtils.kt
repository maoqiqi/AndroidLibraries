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
import android.content.res.Configuration
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment

// Utils about screen.

// Return the width of screen, in pixel.
fun Context.getScreenWidth() = resources.displayMetrics.widthPixels
fun Fragment.getScreenWidth() = requireActivity().getScreenWidth()

// Return the height of screen, in pixel.
fun Context.getScreenHeight() = resources.displayMetrics.heightPixels
fun Fragment.getScreenHeight() = requireActivity().getScreenHeight()

// Return the status bar's height.
fun Context.getStatusBarHeight(): Int {
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId == 0) 0 else resources.getDimensionPixelSize(resourceId)
}
fun Fragment.getStatusBarHeight(): Int = requireActivity().getStatusBarHeight()

// Return the navigation bar's height.
fun Context.getNavBarHeight(): Int {
    val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId == 0) 0 else resources.getDimensionPixelSize(resourceId)
}
fun Fragment.getNavBarHeight(): Int = requireActivity().getNavBarHeight()

// Return whether screen is portrait.
fun Context.isPortrait() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

// Return whether screen is landscape.
fun Context.isLandscape() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Context.getScreenDensityDpi(): String {
    return when (resources.displayMetrics.densityDpi) {
        DisplayMetrics.DENSITY_LOW -> "ldpi"
        DisplayMetrics.DENSITY_MEDIUM -> "mdpi"
        DisplayMetrics.DENSITY_HIGH -> "hdpi"
        DisplayMetrics.DENSITY_XHIGH -> "xhdpi"
        DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi"
        DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi"
        else -> "other"
    }
}
