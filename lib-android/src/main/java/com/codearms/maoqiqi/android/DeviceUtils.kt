package com.codearms.maoqiqi.android

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import java.io.File
import java.util.*

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

    @JvmStatic
    fun getLanguage(): String = Locale.getDefault().language

    fun getOsVersion(): String = Build.VERSION.RELEASE

    // Return the version name of device's system.
    fun getSdkVersionName(): String = Build.VERSION.RELEASE

    // Return version code of device's system.
    fun getSdkVersionCode(): Int = Build.VERSION.SDK_INT

    fun getModel(): String = Build.MODEL

    fun getBrand(): String = Build.BRAND

    fun getBoard(): String = Build.BOARD

    @Suppress("DEPRECATION")
    fun getCpuAbi(): Array<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) Build.SUPPORTED_ABIS else arrayOf(Build.CPU_ABI, Build.CPU_ABI2)

    // 收集设备参数信息
    fun getDeviceInfo(): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        val fields = Build::class.java.declaredFields
        try {
            for (field in fields) {
                field.isAccessible = true
                map[field.name] = field.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return map
    }
}