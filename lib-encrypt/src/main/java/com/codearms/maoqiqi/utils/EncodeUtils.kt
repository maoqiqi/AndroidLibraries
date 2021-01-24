package com.codearms.maoqiqi.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset

class EncodeUtils {

    @Throws(UnsupportedEncodingException::class)
    fun String.urlEncode(charset: Charset? = Charsets.UTF_8) {
        URLEncoder.encode(this, charset?.name())
    }

    @Throws(UnsupportedEncodingException::class)
    fun String.urlDecode(charset: Charset? = Charsets.UTF_8) {
        val safeInput: String = this.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25").replace("\\+".toRegex(), "%2B")
        URLDecoder.decode(safeInput, charset?.name())
    }

    fun ByteArray.base64Encode() {
        Base64.encode(this, Base64.NO_WRAP)
    }

    fun String.base64Encode() {
        Base64.encode(this.toByteArray(), Base64.NO_WRAP)
    }

    fun String.base64Encode2String() {
        Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
    }

    fun ByteArray.base64Decode() {
        Base64.decode(this, Base64.NO_WRAP)
    }

    fun String.base64Decode() {
        Base64.decode(this, Base64.NO_WRAP)
    }
}