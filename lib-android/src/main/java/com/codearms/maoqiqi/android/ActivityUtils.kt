package com.codearms.maoqiqi.android

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View

/**
 * Utils about activity
 * link: https://github.com/maoqiqi/AndroidLibraries
 * author: March
 * date: 2021-02-02 21:01
 * version v1.0.0
 */
object ActivityUtils {

    /**
     * Return the activity by context.
     * @return the activity by context
     */
    @JvmStatic
    fun Context?.getActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    /**
     * Return the activity by view.
     * @return the activity by view
     */
    @JvmStatic
    fun View?.getActivity(): Activity? = this?.context?.getActivity()
}