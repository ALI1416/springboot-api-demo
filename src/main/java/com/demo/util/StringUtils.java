package com.demo.util;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * <h1>字符串工具</h1>
 *
 * <p>
 * createDate 2020/11/18 10:29:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Component
public class StringUtils {

    public static void main(String[] args) {
        System.out.println(getRandom(BASE64_ALPHABET, 100));
        System.out.println(getAnsj("`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?广西壮族自治区桂林市七星区桂林航天工业学院南校区"));
        System.out.println(getMask("123456"));
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
     * 64进制:{@value}
     */
    public final static String BASE64_ALPHABET = NUMBER + ALL_LETTER + "+/";

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
     * @see #BASE64_ALPHABET
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
     * @see #getRandom(String, int)
     * @see #NUMBER
     */
    public static String getRandomNum(int len) {
        return getRandom(NUMBER, len);
    }

    /**
     * 获取4位随机数字字符串
     *
     * @see #getRandom(String, int)
     * @see #NUMBER
     */
    public static String getRandomNum4() {
        return getRandom(NUMBER, 4);
    }

    /**
     * 获取6位随机数字字符串
     *
     * @see #getRandom(String, int)
     * @see #NUMBER
     */
    public static String getRandomNum6() {
        return getRandom(NUMBER, 6);
    }

    /**
     * 获取8位随机数字字符串
     *
     * @see #getRandom(String, int)
     * @see #NUMBER
     */
    public static String getRandomNum8() {
        return getRandom(NUMBER, 8);
    }

    /**
     * 获取ansj分词
     *
     * @param s 要分词的字符串
     * @see org.ansj.splitWord.analysis.ToAnalysis#parse(String)
     */
    public static String getAnsj(String s) {
        // 标准分词 没有词性 去掉英文标点符号 多个空格合成1个 去除首尾空格
        return ToAnalysis.parse(s).toStringWithOutNature(" ").replaceAll("[\\pP`=~$^+|<>]", "").replaceAll(" +", " ").trim();
    }

    /**
     * 获取64位uuid(去除-)
     *
     * @see java.util.UUID#randomUUID()
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

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

    /**
     * 字符串打码
     *
     * @param string 原始字符串
     * @param mask   马赛克字符串
     * @param start  字符串保留首部长度
     * @param end    字符串保留尾部长度
     * @see java.lang.StringBuilder#replace(int start, int end, String str)
     */
    public static String getMask(String string, String mask, int start, int end) {
        StringBuilder sb = new StringBuilder(string);
        // 字符串长度
        int len = sb.length();
        if (len <= start + end) {
            // 字符串长度<=首部保留长度+尾部保留长度
            return mask;
        } else {
            return sb.replace(start, len - end, mask).toString();
        }
    }

    /**
     * 字符串打码(保留首尾1位，马赛克字符串是"***")
     *
     * @param string 原始字符串
     * @see #getMask(String string, String mask, int start, int end)
     */
    public static String getMask(String string) {
        return getMask(string, "***", 1, 1);
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 文件名
     */
    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".") + 1;
        return index == 0 ? "" : fileName.substring(index);
    }

    /**
     * 重命名字符串
     *
     * @param stringList 字符串列表
     */
    public static List<String> duplicateRenameStr(List<String> stringList) {
        return duplicateRename(stringList, false);
    }

    /**
     * 重命名文件名
     *
     * @param fileName 文件名列表
     */
    public static List<String> duplicateRenameFile(List<String> fileName) {
        return duplicateRename(fileName, true);
    }

    /**
     * 重命名
     *
     * @param stringList 字符串或文件名列表
     * @param isFile     是文件
     */
    public static List<String> duplicateRename(List<String> stringList, boolean isFile) {
        List<String> strTemp = new ArrayList<>();
        List<String> strResult = new ArrayList<>();
        List<Integer> strCount = new ArrayList<>();
        for (String s : stringList) {
            if (!strResult.contains(s)) {
                strTemp.add(s);
                strResult.add(s);
                strCount.add(1);
            } else {
                int index = strTemp.indexOf(s);
                Integer count = strCount.get(index);
                String countStr;
                if (isFile) {
                    int dot = s.lastIndexOf(".");
                    if (dot == -1) {
                        countStr = s + "(" + count + ")";
                    } else {
                        countStr = s.substring(0, dot) + "(" + count + ")" + s.substring(dot);
                    }
                } else {
                    countStr = s + "(" + count + ")";
                }
                strCount.set(index, count + 1);
                strResult.add(countStr);
            }
        }
        // 没有重复的
        if (stringList.size() == strTemp.size()) {
            return stringList;
        }
        return strResult;
    }

}
