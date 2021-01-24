package com.codearms.maoqiqi.utils

import java.util.*

object ConvertUtils {

    private val HEX_DIGITS_UPPER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    private val HEX_DIGITS_LOWER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

    // Hex string to int.
    fun String.hex2Int() = toInt(16)

    // Bytes to hex string.
    @JvmStatic
    @JvmOverloads
    fun ByteArray?.toHexString(isUpperCase: Boolean = true): String? {
        if (this == null) return null
        if (this.isEmpty()) return ""
        val hexDigits = if (isUpperCase) HEX_DIGITS_UPPER else HEX_DIGITS_LOWER
        val ret = CharArray(this.size shl 1)
        var i = 0
        for (b in this) {
            ret[i++] = hexDigits[b.toInt() ushr 4 and 0x0f]
            ret[i++] = hexDigits[b.toInt() and 0x0f]
        }
        return String(ret)
    }

    fun String.hex2Bytes(): ByteArray {
        val hexString: String = if (length % 2 != 0) "0$this" else this
        val hexBytes: CharArray = hexString.toUpperCase(Locale.getDefault()).toCharArray()
        val ret = ByteArray(hexString.length shr 1)
        for (i in 0..hexString.length step 2) {
            ret[i shr 1] = (hex2Dec(hexBytes[i]) shl 4 or hex2Dec(hexBytes[i + 1])).toByte()
        }
        return ret
    }

    private fun hex2Dec(hexChar: Char): Int {
        return when (hexChar) {
            in '0'..'9' -> hexChar - '0'
            in 'A'..'F' -> hexChar - 'A' + 10
            else -> throw IllegalArgumentException()
        }
    }
}