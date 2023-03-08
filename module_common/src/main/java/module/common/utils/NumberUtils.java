package module.common.utils;

import java.util.regex.Pattern;

public class NumberUtils {

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
