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