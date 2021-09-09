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

    // Return the version name of device's system.
    fun getOsVersionName(): String = Build.VERSION.RELEASE

    // Return version code of device's system.
    fun getOsVersionCode(): Int = Build.VERSION.SDK_INT

    // https://developer.android.google.cn/guide/topics/manifest/uses-sdk-element#ApiLevels
    fun getOsVersionMapping(): String = when (Build.VERSION.SDK_INT) {
        1 -> "Android 1.0"
        2 -> "Android 1.1 Petit Four"
        3 -> "Android 1.5 Cupcake"
        4 -> "Android 1.6 Donut"
        5 -> "Android 2.0 Eclair"
        6 -> "Android 2.0.1 Eclair"
        7 -> "Android 2.1 Eclair"
        8 -> "Android 2.2 Froyo"
        9 -> "Android 2.3 Gingerbread"
        10 -> "Android 2.3.3 Gingerbread"
        11 -> "Android 3.0 Honeycomb"
        12 -> "Android 3.1 Honeycomb"
        13 -> "Android 3.2 Honeycomb"
        14 -> "Android 4.0 IceCreamSandwich"
        15 -> "Android 4.0.3 IceCreamSandwich"
        16 -> "Android 4.1 Jelly Bean"
        17 -> "Android 4.2 Jelly Bean"
        18 -> "Android 4.3 Jelly Bean"
        19 -> "Android 4.4 KitKat"
        20 -> "Android 4.4W KitKat Wear"
        21 -> "Android 5.0 Lollipop"
        22 -> "Android 5.1 Lollipop"
        23 -> "Android 6.0 Marshmallow"
        24 -> "Android 7.0 Nougat"
        25 -> "Android 7.1.1 Nougat"
        26 -> "Android 8.0 Oreo"
        27 -> "Android 8.1 Oreo"
        28 -> "Android 9.0 Pie"
        29 -> "Android 10.0 Q"
        30 -> "Android 11.0 R"
        else -> "Android 11.0+"
    }

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