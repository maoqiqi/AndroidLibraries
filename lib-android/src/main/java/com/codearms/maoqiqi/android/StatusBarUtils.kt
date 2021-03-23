package com.codearms.maoqiqi.android

import android.app.Activity
import android.os.Build
import android.view.View

object StatusBarUtils {

    @JvmOverloads
    @JvmStatic
    @Suppress("DEPRECATION")
    fun Activity.setFullScreen(isShowStatusBar: Boolean = false, isShowNavigationBar: Boolean = false) {
        // View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN:可以让布局延伸到状态栏的位置
        // View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION:可以让布局延伸到导航栏的位置
        // View.SYSTEM_UI_FLAG_IMMERSIVE:避免某些用户交互造成系统自动清除全屏状态
        // View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY:避免某些用户交互造成系统自动清除全屏状态,同时Activity的部分内容也因此被StatusBar覆盖遮挡
        // View.SYSTEM_UI_FLAG_LAYOUT_STABLE:保持布局稳定,避免在显隐状态栏导航栏的时候发生布局的变化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            var uiOptions: Int = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            if (!isShowStatusBar) {
                uiOptions = uiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
            if (!isShowNavigationBar) {
                uiOptions = uiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
            window.decorView.systemUiVisibility = uiOptions
        }
    }

    @JvmStatic
    @Suppress("DEPRECATION")
    fun Activity.getSystemUiVisibility(): Int = this.window.decorView.systemUiVisibility

    @JvmOverloads
    @JvmStatic
    @Suppress("DEPRECATION")
    fun Activity.setStatusBarModel(uiOptions: Int, darkStatusBar: Boolean = false) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val option = if (darkStatusBar) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.decorView.systemUiVisibility = uiOptions or option
        }
    }
}