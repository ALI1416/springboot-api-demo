package com.demo.controller;

/**
 * <h1>基api</h1>
 *
 * <p>
 * createDate 2021/01/19 20:12:18
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class BaseController {

    /**
     * 是null对象
     *
     * @param obj 对象
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 存在null对象
     *
     * @param objs 对象
     */
    public static boolean existNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是空字符串
     *
     * @param string 字符串
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    /**
     * 存在空字符串
     *
     * @param strings 字符串
     */
    public static boolean existEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是空白字符串
     *
     * @param string 字符串
     */
    public static boolean isBlack(String string) {
        return string == null || string.trim().length() == 0;
    }

    /**
     * 存在空白字符串
     *
     * @param strings 字符串
     */
    public static boolean existBlack(String... strings) {
        for (String string : strings) {
            if (string == null || string.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

}