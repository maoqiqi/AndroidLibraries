package com.codearms.maoqiqi.android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle

object IntentUtils {

    @JvmStatic
    fun Context.isIntentAvailable(intent: Intent) =
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0

    @JvmStatic
    @JvmOverloads
    fun <T : Activity> Context.startActivity(clazz: Class<out T>, extras: Bundle? = null, options: Bundle? = null) {
        startActivity(createIntent(clazz, extras), options)
    }

    @JvmStatic
    @JvmOverloads
    fun <T : Activity> Context.createIntent(clazz: Class<out T>, extras: Bundle? = null): Intent {
        return Intent(this, clazz).apply { extras?.let { putExtras(it) } }
    }
}