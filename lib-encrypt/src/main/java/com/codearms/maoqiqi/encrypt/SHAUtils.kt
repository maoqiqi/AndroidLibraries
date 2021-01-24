package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.EncryptUtils.hashTemplate
import java.security.NoSuchAlgorithmException

/**
 * SHA encryption
 * author: March
 * date: 2021-01-24 21:01
 * version v1.0.0
 */
object SHAUtils {

    /**
     * Return the bytes of SHA1 encryption.
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.sha1(): ByteArray? = hashTemplate("SHA-1")

    /**
     * Return the bytes of SHA224 encryption.
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.sha224(): ByteArray? = hashTemplate("SHA-224")

    /**
     * Return the bytes of SHA256 encryption.
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.sha256(): ByteArray? = hashTemplate("SHA-256")

    /**
     * Return the bytes of SHA384 encryption.
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.sha384(): ByteArray? = hashTemplate("SHA-384")

    /**
     * Return the bytes of SHA512 encryption.
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun ByteArray?.sha512(): ByteArray? = hashTemplate("SHA-512")
}