package com.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.ansj.splitWord.analysis.ToAnalysis;
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
        System.out.println(getRandom(BASE64_ALPHABET, 100));
        System.out.println(getRandomNum4());
        System.out.println(getRandomNum6());
        System.out.println(getRandomNum8());
        System.out.println(getAnsj("%&Y公告HY*82人98发顺丰39&发%……&#人￥&发（）——*）（&（%……@广西壮族自治区桂林市七星区桂林航天工业学院南校区{【】{}“：》《？"));
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

    /**
     * 获取ansj分词
     *
     * @param s 要分词的字符串
     */
    public static String getAnsj(String s) {
        return ToAnalysis.parse(s).toStringWithOutNature(" ").replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "").replaceAll(" +", " ").trim();
    }

    /**
     * 获取64位uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 字符串打码
     *
     * @param str 要打码的字符串
     */
    public static String getMask(String str) {
        String mask = "***";
        StringBuilder sb = new StringBuilder(str);
        if (sb.length() > 2) {
            return sb.replace(1, sb.length() - 1, mask).toString();
        }
        return mask;
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 文件名
     */
    public static String getSuffix(String fileName) {
        int a = fileName.lastIndexOf(".") + 1;
        return a == 0 ? "" : fileName.substring(a);
    }

    /**
     * 重命名字符串
     *
     * @param str 字符串列表
     */
    public static List<String> duplicateRenameStr(List<String> str) {
        return duplicateRename(str, false);
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
     * @param str    字符串或文件名列表
     * @param isFile 是文件
     */
    public static List<String> duplicateRename(List<String> str, boolean isFile) {
        List<String> strTemp = new ArrayList<>();
        List<String> strResult = new ArrayList<>();
        List<Integer> strCount = new ArrayList<>();
        for (String s : str) {
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
        if (str.size() == strTemp.size()) {
            return str;
        }
        return strResult;
    }

}
