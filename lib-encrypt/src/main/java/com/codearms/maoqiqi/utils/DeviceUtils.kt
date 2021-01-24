package com.codearms.maoqiqi.utils

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import java.io.File

object DeviceUtils {

    // Return whether device is rooted.
    fun isDeviceRooted(): Boolean {
        val su = "su"
        val locations = arrayOf(
            "/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
            "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"
        )
        for (location in locations) if (File(location + su).exists()) return true
        return false
    }

    // Return whether ADB is enabled.
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun Context.isAdbEnabled(): Boolean {
        return Settings.Secure.getInt(this.contentResolver, Settings.Global.ADB_ENABLED, 0) > 0
    }

    // Return the version name of device's system.
    fun getSDKVersionName(): String = Build.VERSION.RELEASE

    // Return version code of device's system.
    fun getSDKVersionCode(): Int = Build.VERSION.SDK_INT

}