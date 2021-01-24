package com.codearms.maoqiqi.encrypt

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

fun Context.isIntentAvailable(intent: Intent) =
    packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0