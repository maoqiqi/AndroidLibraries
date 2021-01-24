package com.codearms.maoqiqi.encrypt

import com.codearms.maoqiqi.encrypt.ByteArrayUtils.isNullOrEmpty
import com.codearms.maoqiqi.encrypt.ByteArrayUtils.joins
import java.security.Key
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptUtils {

    /**
     * Return the bytes of hash encryption.
     * @param data The data
     * @param algorithm The name of hash encryption
     * @return the bytes of hash encryption
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun hashTemplate(data: ByteArray?, algorithm: String?): ByteArray? {
        if (data == null || data.isEmpty() || algorithm.isNullOrEmpty()) return null
        return MessageDigest.getInstance(algorithm).digest(data)
    }

    /**
     * Return the bytes of hmac encryption.
     * @param data The data
     * @param key The key
     * @param algorithm The name of hmac encryption.
     * @return the bytes of hmac encryption
     */
    fun hmacTemplate(data: ByteArray?, key: ByteArray?, algorithm: String?): ByteArray? {
        if (data.isNullOrEmpty() || key.isNullOrEmpty() || algorithm.isNullOrEmpty()) return null
        val secretKey = SecretKeySpec(key, algorithm)
        val mac = Mac.getInstance(algorithm)
        mac.init(secretKey)
        return mac.doFinal(data)
    }

    /**
     * Return the bytes of RSA encryption or decryption.
     * @param data The data
     * @param key The key
     * @param keySize The size of key, e.g. 1024, 2048...
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS1Padding</i>
     * @param isEncrypt True to encrypt, false otherwise.
     * @return the bytes of RSA encryption or decryption
     */
    @JvmStatic
    fun rsaTemplate(data: ByteArray?, key: ByteArray?, keySize: Int, transformation: String?, isEncrypt: Boolean): ByteArray? {
        if (data == null || data.isEmpty() || key.isNullOrEmpty() || transformation.isNullOrEmpty()) return null
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(key)
        val rsaKey: Key = if (isEncrypt) keyFactory.generatePublic(keySpec) else keyFactory.generatePrivate(keySpec)

        val cipher = Cipher.getInstance(transformation)
        cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, rsaKey)
        val len = data.size
        var maxLen = keySize / 8
        if (isEncrypt) {
            val lowerTrans = transformation.toLowerCase(Locale.getDefault())
            if (lowerTrans.endsWith("pkcs1padding")) maxLen -= 11
        }
        val count = len / maxLen
        return if (count > 0) {
            var ret: ByteArray? = null
            var buff = ByteArray(maxLen)
            var index = 0
            for (i in 0 until count) {
                System.arraycopy(data, index, buff, 0, maxLen)
                ret = ret.joins(cipher.doFinal(buff))
                index += maxLen
            }
            if (index != len) {
                val restLen = len - index
                buff = ByteArray(restLen)
                System.arraycopy(data, index, buff, 0, restLen)
                ret = ret.joins(cipher.doFinal(buff))
            }
            ret
        } else cipher.doFinal(data)
    }

    /**
     * Return the bytes of symmetric encryption or decryption.
     * @param data The data
     * @param key The key
     * @param algorithm The name of algorithm
     * @param transformation The name of the transformation, e.g., <i>DES/CBC/PKCS5Padding</i>.
     * @param iv The algorithm parameters
     * @param isEncrypt True to encrypt, false otherwise.
     * @return the bytes of symmetric encryption or decryption.
     */
    @JvmStatic
    fun symmetricTemplate(data: ByteArray?, key: ByteArray?, algorithm: String?, transformation: String?, iv: ByteArray?, isEncrypt: Boolean): ByteArray? {
        if (data.isNullOrEmpty() || key.isNullOrEmpty() || algorithm.isNullOrEmpty() || transformation.isNullOrEmpty()) return null
        val secretKey: SecretKey = if ("DES" == algorithm) {
            SecretKeyFactory.getInstance(algorithm).generateSecret(DESKeySpec(key))
        } else SecretKeySpec(key, algorithm)

        val cipher = Cipher.getInstance(transformation)
        if (iv.isNullOrEmpty()) {
            cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, secretKey)
        } else {
            val params: AlgorithmParameterSpec = IvParameterSpec(iv)
            cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, secretKey, params)
        }
        return cipher.doFinal(data)
    }
}