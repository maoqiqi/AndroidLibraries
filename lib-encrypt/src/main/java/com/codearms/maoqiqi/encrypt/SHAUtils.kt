package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.EncryptUtils.hashTemplate

object SHAUtils {

    /**
     * Return the bytes of SHA1 encryption.
     */
    fun ByteArray?.sha1(): ByteArray? {
        return hashTemplate(this, "SHA-1")
    }

    /**
     * Return the bytes of SHA224 encryption.
     */
    fun ByteArray?.sha224(): ByteArray? {
        return hashTemplate(this, "SHA-224")
    }

    /**
     * Return the bytes of SHA256 encryption.
     */
    fun ByteArray?.sha256(): ByteArray? {
        return hashTemplate(this, "SHA-256")
    }

    /**
     * Return the bytes of SHA384 encryption.
     */
    fun ByteArray?.sha384(): ByteArray? {
        return hashTemplate(this, "SHA-384")
    }

    /**
     * Return the bytes of SHA512 encryption.
     */
    fun ByteArray?.sha512(): ByteArray? {
        return hashTemplate(this, "SHA-512")
    }
}