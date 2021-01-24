package com.codearms.maoqiqi.encrypt

object DESUtils {

    private const val DES = "DES"
    private const val DES3 = "DESede"

    /**
     * Return the bytes of DES encryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of DES encryption
     */
    fun ByteArray?.desEncrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return EncryptUtils.symmetricTemplate(this, key, DES, transformation, iv, true)
    }

    /**
     * Return the bytes of DES decryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of DES decryption
     */
    fun ByteArray?.desDecrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return EncryptUtils.symmetricTemplate(this, key, DES, transformation, iv, false)
    }

    /**
     * Return the bytes of 3DES encryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of 3DES encryption
     */
    fun ByteArray?.des3Encrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return EncryptUtils.symmetricTemplate(this, key, DES3, transformation, iv, true)
    }

    /**
     * Return the bytes of 3DES decryption.
     * @param key The key
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @return the bytes of 3DES decryption
     */
    fun ByteArray?.des3Decrypt(key: ByteArray?, transformation: String, iv: ByteArray?): ByteArray? {
        return EncryptUtils.symmetricTemplate(this, key, DES3, transformation, iv, false)
    }
}