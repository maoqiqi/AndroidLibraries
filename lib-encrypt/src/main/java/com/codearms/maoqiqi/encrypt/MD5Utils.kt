package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.ByteArrayUtils.joins
import com.codearms.maoqiqi.encrypt.EncryptUtils.hashTemplate
import java.io.File
import java.io.FileInputStream
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5Utils {

    private const val MD5 = "MD5"

    /**
     * Return the bytes of MD5 encryption.
     * @param salt The salt
     * @return the bytes of MD5 encryption
     */
    @JvmStatic
    @JvmOverloads
    fun ByteArray?.md5(salt: ByteArray? = null): ByteArray? {
        return hashTemplate(this.joins(salt), MD5)
    }

    /**
     * Return the hex string of MD5 encryption.
     * @param charset
     * @return the hex string of MD5 encryption
     */
    @JvmStatic
    @JvmOverloads
    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun String?.md5(charset: Charset? = Charsets.UTF_8): ByteArray? = this?.toByteArray(charset ?: Charsets.UTF_8).md5()

    /**
     * Return whether the string is null or white space.
     */
    private fun String?.isSpace(): Boolean {
        if (this == null) return true
        for (element in this) if (!Character.isWhitespace(element)) return false
        return true
    }

    /**
     * Return the bytes of file's MD5 encryption.
     */
    fun String?.md5File(): ByteArray? = if (this == null || isSpace()) null else File(this).md5File()

    /**
     * Return the bytes of file's MD5 encryption.
     */
    fun File?.md5File(): ByteArray? {
        if (this == null) return null
        val fis = FileInputStream(this)
        var md = MessageDigest.getInstance(MD5)
        val digestInputStream = DigestInputStream(fis, md)
        val buffer = ByteArray(256 * 1024)
        while (true) if (digestInputStream.read(buffer) <= 0) break
        md = digestInputStream.messageDigest
        return md.digest()
    }
}