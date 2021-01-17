package com.demo.util;

import java.util.regex.Pattern;

/**
 * <h1>正则表达式工具</h1>
 *
 * <p>
 * createDate 2021/01/17 11:09:20
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RegexUtils {

    public static void main(String[] args) {
        System.out.println(isEmail("1416978277@qq.com"));
    }

    /**
     * 用户名只能由字母和数字组成，必须字母开头，长度5-16
     */
    public static boolean isAccount(String s) {
        return Pattern.matches("^[A-Za-z][A-Za-z0-9]{4,15}$", s);
    }

    /**
     * 邮箱
     */
    public static boolean isEmail(String s) {
        return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", s);
    }

}
