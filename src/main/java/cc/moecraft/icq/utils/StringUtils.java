package cc.moecraft.icq.utils;

import java.util.Random;

public class StringUtils {
    private static final char[] ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int rnd = new Random().nextInt(ALPHA_NUM.length);
            sb.append(ALPHA_NUM[rnd]);
        }
        return sb.toString();
    }
}
