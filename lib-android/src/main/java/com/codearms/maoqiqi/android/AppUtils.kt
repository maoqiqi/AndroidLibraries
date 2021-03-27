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
import android.content.pm.PackageManager
import android.os.Build
import androidx.fragment.app.Fragment

/**
 * Return the application's version name.
 * @return the application's version name
 */
@Throws(PackageManager.NameNotFoundException::class)
fun Context?.getAppVersionName(): String? {
    val pm: PackageManager = this?.packageManager ?: return null
    return pm.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES).versionName
}

@Throws(PackageManager.NameNotFoundException::class)
fun Fragment.getAppVersionName(): String? = requireActivity().getAppVersionName()

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

@Throws(PackageManager.NameNotFoundException::class)
fun Fragment.getAppVersionCode(): Long = requireActivity().getAppVersionCode()
