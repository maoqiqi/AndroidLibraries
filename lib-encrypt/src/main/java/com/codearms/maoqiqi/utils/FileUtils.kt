package com.codearms.maoqiqi.utils

import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {

    fun saveLogToFile(path: String, data: String): String? {
        try {
            val time = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA).format(Date())
            val fileName = "crash-$time.log"
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val dir = File(Environment.getExternalStorageDirectory(), path)
                if (!dir.exists()) {
                    val flag = dir.mkdir()
                }
                val fos = FileOutputStream(File(dir, fileName))
                fos.write(data.toByteArray())
            }
            return fileName
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}