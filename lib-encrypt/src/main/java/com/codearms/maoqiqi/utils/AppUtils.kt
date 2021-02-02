package com.codearms.maoqiqi.utils

import android.content.Context
import android.content.pm.PackageManager

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
    @Throws(PackageManager.NameNotFoundException::class)
    fun Context?.getAppVersionCode(): Long {
        val pm: PackageManager = this?.packageManager ?: return 0
        return pm.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES).longVersionCode
    }
}
