package com.codearms.maoqiqi.encrypt

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics

// Utils about screen.

// Return the width of screen, in pixel.
fun Context.getScreenWidth() = resources.displayMetrics.widthPixels

// Return the height of screen, in pixel.
fun Context.getScreenHeight() = resources.displayMetrics.heightPixels

// Return the status bar's height.
fun Context.getStatusBarHeight(): Int {
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId == 0) 0 else resources.getDimensionPixelSize(resourceId)
}

// Return the navigation bar's height.
fun Context.getNavBarHeight(): Int {
    val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId == 0) 0 else resources.getDimensionPixelSize(resourceId)
}

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