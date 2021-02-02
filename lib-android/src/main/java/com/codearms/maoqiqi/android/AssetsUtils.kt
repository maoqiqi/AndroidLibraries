package com.codearms.maoqiqi.android

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object AssetsUtils {

    fun Context?.getJson(fileName: String?): String {
        if (this == null || fileName == null) return ""
        try {
            val stringBuilder = StringBuilder()
            val inputStream: InputStream = this.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            return stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}