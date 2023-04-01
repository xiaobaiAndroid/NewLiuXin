package module.common.data.security


import android.util.Base64
import java.security.Key
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


/**
 *@author: baizf
 *@date: 2023/3/31
 */
object RSABroker {

    const val KEY_ALGORITHM = "RSA"
    private const val PUBLIC_KEY = "RSAPublicKey"
    private const val PRIVATE_KEY = "RSAPrivateKey"


    /**
     * 私钥解密
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun decryptByPrivateKey(data: String?, privateKey: String?): String? {
        val keyBytes = Base64.decode(privateKey, Base64.NO_WRAP)
        val pkcs8KeySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val privateK: Key = keyFactory.generatePrivate(pkcs8KeySpec)
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.DECRYPT_MODE, privateK)
        val buff = cipher.doFinal(Base64.decode(data, Base64.NO_WRAP))
        return String(buff)
    }


    /**
     * 公钥解密
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun decryptByPublicKey(data: String?, publicKey: String?): String? {
        val keyBytes = Base64.decode(publicKey, Base64.NO_WRAP)
        val x509KeySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val publicK: Key = keyFactory.generatePublic(x509KeySpec)
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.DECRYPT_MODE, publicK)
        // 执行解密操作
        val buff = cipher.doFinal(Base64.decode(data, Base64.NO_WRAP))
        return String(buff)
    }


    /**
     * 公钥加密
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encryptByPublicKey(data: String, publicKey: String?): String? {
        val keyBytes = Base64.decode(publicKey, Base64.NO_WRAP)
        val x509KeySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val publicK: Key = keyFactory.generatePublic(x509KeySpec)
        // 对数据加密
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, publicK)
        val buff = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(buff, Base64.NO_WRAP)
    }


    /**
     * 私钥加密
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encryptByPrivateKey(data: String, privateKey: String?): String? {
        val keyBytes = Base64.decode(privateKey, Base64.NO_WRAP)
        val pkcs8KeySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance(KEY_ALGORITHM)
        val privateK: Key = keyFactory.generatePrivate(pkcs8KeySpec)
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, privateK)
        val buff = cipher.doFinal(data.toByteArray())
        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输
        return Base64.encodeToString(buff, Base64.NO_WRAP)
    }


    /**
     * 获取私钥
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getPrivateKey(keyMap: Map<String?, Any?>): String? {
        val key = keyMap[PRIVATE_KEY] as Key?
        return Base64.encodeToString(key!!.encoded, Base64.NO_WRAP)
    }


    /**
     * 获取公钥
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getPublicKey(keyMap: Map<String?, Any?>): String? {
        val key = keyMap[PUBLIC_KEY] as Key?
        return Base64.encodeToString(key!!.encoded, Base64.NO_WRAP)
    }


    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun initKey(): Map<String, Any> {
        val keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM)
        keyPairGen.initialize(1024)
        val keyPair = keyPairGen.generateKeyPair()
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey
        val keyMap: MutableMap<String, Any> = HashMap(2)
        keyMap[PUBLIC_KEY] = publicKey
        keyMap[PRIVATE_KEY] = privateKey
        return keyMap
    }


}