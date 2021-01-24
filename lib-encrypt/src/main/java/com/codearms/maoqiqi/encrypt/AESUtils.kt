package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.EncryptUtils.symmetricTemplate

object AESUtils {

    private const val AES = "AES"

    /**
     * Return the bytes of AES encryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of AES encryption
     */
    fun ByteArray?.aesEncrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return symmetricTemplate(this, key, AES, transformation, iv, true)
    }

    /**
     * Return the bytes of AES decryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of AES decryption
     */
    fun ByteArray?.aesDecrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return symmetricTemplate(this, key, AES, transformation, iv, false)
    }
}