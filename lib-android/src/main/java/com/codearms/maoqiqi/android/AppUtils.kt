package com.codearms.maoqiqi.android

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

object AppUtils {

    /**
     * Return the application's version name.
     * @return the application's version name
     */
    @Throws(PackageManager.NameNotFoundException::class)
    fun Context?.getAppVersionName(): String? {
        val pm: PackageManager = this?.packageManager ?: return null
        return pm.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES).versionName
    }

    /**
     * Return the application's version code.
     * @return the application's version code
     */
    @Suppress("DEPRECATION")
    @Throws(PackageManager.NameNotFoundException::class)
    fun Context?.getAppVersionCode(): Long {
        val pm: PackageManager = this?.packageManager ?: return 0
        return pm.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES).let {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) it.versionCode.toLong() else it.longVersionCode
        }
    }
}
