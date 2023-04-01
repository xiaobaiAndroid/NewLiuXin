package module.common.data.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import net.sqlcipher.database.SupportFactory
import java.security.KeyStore
import java.security.KeyStore.SecretKeyEntry
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


/**
 *@author: baizf
 *@date: 2023/3/31
 */
object SQLCipherUtils {

    //秘钥别名应该由服务器加密传输过来
    const val SQL_AES = "liuxin_sqlcipher_key_alias"

    const val KEYSTORE_PROVIDER = "AndroidKeyStore"

    fun getSQLCipherFactory(): SupportFactory?{

        try {
            //创建 KeyPairGenerator 对象
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
            keyStore.load(null)

            var secretKey: SecretKey? = null
            if(keyStore.containsAlias(SQL_AES)){
               val secretKeyEntry  = keyStore.getEntry(SQL_AES, null) as SecretKeyEntry
                secretKey = secretKeyEntry.secretKey
            }else{
                secretKey = generateKey()
            }


            return secretKey?.let {
                    SupportFactory(it.encoded, null)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    /*
    * @describe: 生成AES秘钥
    * @date: 2023/3/31
    */
    fun generateKey(): SecretKey?{
        try {
            // 构造 KeyGenParameterSpec 对象，指定密钥的参数
            val keySpec = KeyGenParameterSpec.Builder(SQL_AES,KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setKeySize(256)
                .build()

            //AndroidKeyStore 支持的大小：128、192、256
            val keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,KEYSTORE_PROVIDER)
            keyGenerator.init(keySpec)
            return keyGenerator.generateKey()
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}