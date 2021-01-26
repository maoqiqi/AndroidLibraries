package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.ByteArrayUtils.joins
import com.codearms.maoqiqi.encrypt.EncryptUtils.hashTemplate
import com.codearms.maoqiqi.utils.ConvertUtils.toHexString
import java.io.*
import java.nio.charset.Charset
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * MD5 encryption
 * author: March
 * date: 2021-01-24 21:01
 * version v1.0.0
 */
object MD5Utils {

    private const val MD5 = "MD5"

    /**
     * Return the bytes of MD5 encryption.
     * @param salt The salt
     * @return the bytes of MD5 encryption
     */
    @JvmStatic
    @JvmOverloads
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.bytesToMd5(salt: ByteArray? = null): ByteArray? = this.joins(salt).hashTemplate(MD5)

    /**
     * Return the hex string of MD5 encryption.
     * @param charset
     * @return the hex string of MD5 encryption
     */
    @JvmStatic
    @JvmOverloads
    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun String?.strToMd5(charset: Charset? = Charsets.UTF_8, salt: String? = null): String? =
        this?.toByteArray(charset ?: Charsets.UTF_8).bytesToMd5(salt?.toByteArray(charset ?: Charsets.UTF_8)).toHexString()

    /**
     * Return whether the string is null or white space.
     * @return True to is white space, false otherwise
     */
    private fun String?.isSpace(): Boolean {
        if (this == null) return true
        for (element in this) if (Character.isWhitespace(element)) return true
        return false
    }

    /**
     * Return the bytes of file's MD5 encryption.
     * @return the bytes of file's MD5 encryption
     */
    @JvmStatic
    @Throws(FileNotFoundException::class, NoSuchAlgorithmException::class, IOException::class)
    fun String?.filePathToMd5(): String? = if (this == null || isSpace()) null else File(this).fileToMd5().toHexString()

    /**
     * Return the bytes of file's MD5 encryption.
     * @return the bytes of file's MD5 encryption.
     */
    @JvmStatic
    @Throws(FileNotFoundException::class, NoSuchAlgorithmException::class, IOException::class)
    fun File?.fileToMd5(): ByteArray? {
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