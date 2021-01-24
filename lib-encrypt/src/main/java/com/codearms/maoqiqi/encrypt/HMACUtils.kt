package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.EncryptUtils.hmacTemplate

object HMACUtils {

    /**
     * Return the bytes of HmacMD5 encryption.
     * @param key The key
     * @return the bytes of HmacMD5 encryption
     */
    fun ByteArray?.hmacMD5Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacMD5")
    }

    /**
     * Return the bytes of HmacSHA1 encryption.
     * @param key The key
     * @return the bytes of HmacSHA1 encryption
     */
    fun ByteArray?.hmacSHA1Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacSHA1")
    }

    /**
     * Return the bytes of HmacSHA224 encryption.
     * @param key The key
     * @return the bytes of HmacSHA224 encryption
     */
    fun ByteArray?.hmacSHA224Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacSHA224")
    }

    /**
     * Return the bytes of HmacSHA256 encryption.
     * @param key The key
     * @return the bytes of HmacSHA256 encryption
     */
    fun ByteArray?.hmacSHA256Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacSHA256")
    }

    /**
     * Return the bytes of HmacSHA384 encryption.
     * @param key The key
     * @return the bytes of HmacSHA384 encryption
     */
    fun ByteArray?.hmacSHA384Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacSHA384")
    }

    /**
     * Return the bytes of HmacSHA512 encryption.
     * @param key The key
     * @return the bytes of HmacSHA512 encryption
     */
    fun ByteArray?.hmacSHA512Encrypt(key: ByteArray?): ByteArray? {
        return hmacTemplate(this, key, "HmacSHA512")
    }
}