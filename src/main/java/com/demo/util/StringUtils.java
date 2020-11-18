package com.demo.util;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * <h1>字符串工具类</h1>
 *
 * <p>
 * createDate 2020/11/18 10:29:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
@Component
public class StringUtils {
    public static void main(String[] args) {
        System.out.println(getRandom(NUMBER_ALL_LETTER, 100));
        System.out.println(getRandomNum4());
        System.out.println(getRandomNum6());
    }

    /**
     * 数字:{@value}
     */
    public final static String NUMBER = "0123456789";
    /**
     * 大写字母:{@value}
     */
    public final static String UPPER_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 小写字母:{@value}
     */
    public final static String LOWER_LETTER = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 全部字母:{@value}
     */
    public final static String ALL_LETTER = UPPER_LETTER + LOWER_LETTER;
    /**
     * 数字+大写字母:{@value}
     */
    public final static String NUMBER_UPPER_LETTER = NUMBER + UPPER_LETTER;
    /**
     * 数字+小写字母:{@value}
     */
    public final static String NUMBER_LOWER_LETTER = NUMBER + LOWER_LETTER;
    /**
     * 数字+全部字母:{@value}
     */
    public final static String NUMBER_ALL_LETTER = NUMBER + ALL_LETTER;

    /**
     * 随机数实例
     */
    private final static Random RANDOM = new Random();

    /**
     * 获取随机字符串
     * 
     * @param base 源字符串
     * @param len  长度
     * @see #NUMBER
     * @see #UPPER_LETTER
     * @see #LOWER_LETTER
     * @see #ALL_LETTER
     * @see #NUMBER_UPPER_LETTER
     * @see #NUMBER_LOWER_LETTER
     * @see #NUMBER_ALL_LETTER
     */
    public static String getRandom(String base, int len) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            s.append(base.charAt(RANDOM.nextInt(base.length())));
        }
        return s.toString();
    }

    /**
     * 获取随机数字字符串
     * 
     * @param len 长度
     */
    public static String getRandomNum4(int len) {
        return getRandom(NUMBER, len);
    }

    /**
     * 获取4位随机数字字符串
     */
    public static String getRandomNum4() {
        return getRandom(NUMBER, 4);
    }

    /**
     * 获取6位随机数字字符串
     */
    public static String getRandomNum6() {
        return getRandom(NUMBER, 6);
    }

    /**
     * 获取8位随机数字字符串
     */
    public static String getRandomNum8() {
        return getRandom(NUMBER, 8);
    }

}
