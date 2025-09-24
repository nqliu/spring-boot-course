package top.nql.boot.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
//中国大陆手机号码的正则表达式，可能需要根据实际情况进行调整
        String regex = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
/**
 *生成4位随机验证码
 * @return int
 */

            public static int generateCode() {
                return ThreadLocalRandom.current().nextInt(1000, 9999);
            }


}
