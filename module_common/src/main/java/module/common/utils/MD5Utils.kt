package module.common.utils

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @describe: MD5工具类
 * @date: 2020/1/11
 * @author: Mr Bai
 */
object MD5Utils {
    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    fun md5Decode32(content: String): String {
        val hash: ByteArray
        hash = try {
            MessageDigest.getInstance("MD5").digest(content.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("NoSuchAlgorithmException", e)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UnsupportedEncodingException", e)
        }
        //对生成的16字节数组进行补零操作
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            if (b.toInt() and 0xFF < 0x10) {
                hex.append("0")
            }
            hex.append(Integer.toHexString(b.toInt() and 0xFF))
        }
        return hex.toString()
    }
}