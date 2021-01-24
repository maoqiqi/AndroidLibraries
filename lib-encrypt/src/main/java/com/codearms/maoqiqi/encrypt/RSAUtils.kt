package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.EncryptUtils.rsaTemplate

class RSAUtils {

    /**
     * Return the bytes of RSA encryption.
     * @param publicKey The public key
     * @param keySize The size of key, e.g. 1024, 2048...
     * @param transformation The name of the transformation, e.g., <i>RSA/CBC/PKCS1Padding</i>.
     * @return the bytes of RSA encryption.
     */
    fun ByteArray?.rsaEncrypt(publicKey: ByteArray?, keySize: Int, transformation: String?): ByteArray? {
        return rsaTemplate(this, publicKey, keySize, transformation, true)
    }

    /**
     * Return the bytes of RSA decryption.
     * @param privateKey The private key
     * @param keySize The size of key, e.g. 1024, 2048...
     * @param transformation The name of the transformation, e.g., <i>RSA/CBC/PKCS1Padding</i>.
     * @return the bytes of RSA decryption
     */
    fun ByteArray?.rsaDecrypt(privateKey: ByteArray?, keySize: Int, transformation: String?): ByteArray? {
        return rsaTemplate(this, privateKey, keySize, transformation, false)
    }
}