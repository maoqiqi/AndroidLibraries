package com.codearms.maoqiqi.utils

import java.io.Closeable
import java.io.IOException

object CloseUtils {

    // Close the io stream.
    @JvmStatic
    @JvmOverloads
    fun closeIO(vararg closeables: Closeable?, quietly: Boolean = false) {
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    if (!quietly) e.printStackTrace()
                }
            }
        }
    }
}