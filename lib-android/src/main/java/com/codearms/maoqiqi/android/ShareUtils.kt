package com.codearms.maoqiqi.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File


object ShareUtils {

    @JvmStatic
    @JvmOverloads
    fun shareText(context: Context, subject: String? = "分享", text: String? = null) {
        if (subject == null || text == null) return
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // startActivity(intent)这样只能唤醒微信,不能分享
        context.startActivity(Intent.createChooser(intent, subject))
    }

    @JvmStatic
    @JvmOverloads
    fun shareImage(context: Context, subject: String? = "分享", imagePath: String? = null) {
        if (subject == null || imagePath == null) return
        val file = File(imagePath)
        if (!file.exists() || !file.isFile) return
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
        } else Uri.fromFile(file)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        context.startActivity(Intent.createChooser(intent, subject))
    }
}