package com.codearms.maoqiqi.utils

import android.content.Context
import android.widget.Toast

// Utils about toast.
object ToastUtils {

    fun Context.toast(text: CharSequence) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}