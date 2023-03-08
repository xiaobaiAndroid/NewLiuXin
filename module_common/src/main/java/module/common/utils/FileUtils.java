package module.common.utils;

import android.content.Context;

import java.io.InputStream;

/**
 * @describe: 文件工具类
 * @date: 2020/1/3
 * @author: Mr Bai
 */
public class FileUtils {

    //从resources中的raw 文件夹中获取文件并读取数据
    public static String getFromRaw(Context context, int rawId){
        String result = "";
        try {
            InputStream in = context.getResources().openRawResource(rawId);
            //获取文件的字节数
            int lenght = in.available();
            //创建byte数组
            byte[]  buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
