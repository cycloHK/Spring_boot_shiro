package com.stu.spring_boot_shiro.utils;

import java.util.Random;

/**
 * 生成salt 的静态工具类
 */
public class SaltUtils {
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chars[new Random().nextInt(chars.length)];
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);
    }
}
