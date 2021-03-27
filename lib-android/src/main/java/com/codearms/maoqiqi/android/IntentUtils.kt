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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

object IntentUtils {

    @JvmStatic
    fun Context.isIntentAvailable(intent: Intent) =
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0

    @JvmStatic
    @JvmOverloads
    fun <T : Activity> Context.start(clazz: Class<out T>, extras: Bundle? = null, options: Bundle? = null) {
        startActivity(createIntent(clazz, extras), options)
    }

    @JvmStatic
    @JvmOverloads
    fun <T : Activity> Fragment.start(clazz: Class<out T>, extras: Bundle? = null, options: Bundle? = null) {
        requireActivity().start(clazz, extras, options)
    }

    @JvmStatic
    @JvmOverloads
    fun <T : Activity> Context.createIntent(clazz: Class<out T>, extras: Bundle? = null): Intent {
        return Intent(this, clazz).apply { extras?.let { putExtras(it) } }
    }
}