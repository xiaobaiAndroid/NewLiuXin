package module.common.utils;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * @describe: 字符串工具类
 * @date: 2019/9/2
 * @author: Mr Bai
 */
public class StringUtils {

    /**
     * @describe: 包装空字符串
    * @date:2019/9/2
     */
    public static String packNull(String content) {
        if(content==null){
            content = "";
        }
        return content;
    }

    public static boolean isPhoneNo(String phone) {
        String pattern = "^\\d{8}$|^1[\\d]{10}$";
        return Pattern.matches(pattern, phone);
    }

    @NotNull
    public static String format(String format,String number) {
        return  String.format(format, number);
    }

    @Nullable
    public static String phonePartHide(@Nullable String phone) {
        if(!TextUtils.isEmpty(phone)){
            String start = phone.substring(0, 3);
            String end = phone.substring(phone.length() - 4);
            return start+"***"+end;
        }
        return "";
    }
}
