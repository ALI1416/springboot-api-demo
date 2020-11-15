package com.demo.util;

import com.demo.source.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <h1>编码工具类</h1>
 *
 * <p>
 * 其中BCrypt是提取Spring Security的代码<br>
 * </p>
 *
 * <p>
 * createDate 2020/11/14 21:20:10
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 **/
public class EncoderUtils {

    public static void main(String[] args) {
        String rawPassword = "admin";
        System.out.println(rawPassword);

        String md5Password = EncoderUtils.md5(rawPassword);
        System.out.println(md5Password);

        String bCryptEncodePassword = EncoderUtils.bCryptEncode(md5Password);
        System.out.println(bCryptEncodePassword);

        boolean bCryptMatchesResult = EncoderUtils.bCryptMatches(md5Password, bCryptEncodePassword);
        System.out.println(bCryptMatchesResult);

        String base64Password = EncoderUtils.base64Encoder(rawPassword);
        System.out.println(base64Password);

        String base64DecodePassword = EncoderUtils.base64Decoder(base64Password);
        System.out.println(base64DecodePassword);

        // long base62LongRawPassword = 1570283088299L;
        long base62LongRawPassword = 1234567890123456789L;
        System.out.println(base62LongRawPassword);

        String base62LongEncoderPassword = EncoderUtils.base62LongEncoder(base62LongRawPassword);
        System.out.println(base62LongEncoderPassword);

        long base62DecoderPassword = EncoderUtils.base62LongDecoder(base62LongEncoderPassword);
        System.out.println(base62DecoderPassword);

        // long a = System.currentTimeMillis();
        // for (int i = 0; i < 10000000; i++) {
        //     EncoderUtils.base62LongDecoder("1TCKi1nFuNh");
        // }
        // long b = System.currentTimeMillis();
        // System.out.println(b - a);

    }

    /**
     * UTF8字符集编码
     */
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    /**
     * BCrypt实例
     */
    private static final BCryptPasswordEncoder B_CRYPT = new BCryptPasswordEncoder();

    /**
     * BCrypt加密
     *
     * @param rawPassword 原始数据
     */
    public static String bCryptEncode(CharSequence rawPassword) {
        return B_CRYPT.encode(rawPassword);
    }

    /**
     * BCrypt验证
     *
     * @param rawPassword     原始数据
     * @param encodedPassword 加密后的数据
     */
    public static boolean bCryptMatches(CharSequence rawPassword, String encodedPassword) {
        return B_CRYPT.matches(rawPassword, encodedPassword);
    }

    /**
     * MD5加密
     *
     * @param rawPassword 原始数据
     */
    public static String md5(String rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes(UTF8));
    }

    /**
     * base64编码
     *
     * @param rawPassword 原始数据
     */
    public static String base64Encoder(String rawPassword) {
        return Base64.getEncoder().encodeToString(rawPassword.getBytes(UTF8));
    }

    /**
     * base64解码
     *
     * @param encodedPassword 加密后的数据
     */
    public static String base64Decoder(String encodedPassword) {
        return new String(Base64.getDecoder().decode(encodedPassword), UTF8);
    }

    /**
     * base62 Long型编码
     *
     * @param n long型原始数据
     */
    public static String base62LongEncoder(long n) {
        if (n <= 0) {
            return "0";
        }
        StringBuilder s = new StringBuilder();
        long a = 0;
        for (; n > 0; n /= 62) {
            a = (n % 62);
            if (a < 10) {
                s.append((char) (a + 48));// 转为0-9
            } else if (a < 36) {
                s.append((char) (a + 55));// 转为A-Z
            } else {
                s.append((char) (a + 61));// 转为a-z
            }
        }
        return s.reverse().toString();
    }

    /**
     * base62 Long型解码
     *
     * @param s 加密后的数据
     * @return long型
     */
    public static long base62LongDecoder(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        long n = 0;
        long p = 1;
        int c;
        for (int i = s.length() - 1; i >= 0; i--, p *= 62) {
            c = s.charAt(i);
            if (c > 96) {
                n += (c - 61) * p;// a-z转为数字
            } else if (c > 64) {
                n += (c - 55) * p;// A-Z转为数字
            } else {
                n += (c - 48) * p;// 0-9转为数字
            }
        }
        return n;
    }

    public static String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String encoding(long n) {
        if (n < 1) {
            return "0";
        }
        StringBuilder s = new StringBuilder();
        for (; n > 0; n /= 62) {
            s.append(BASE62_ALPHABET.charAt((int) (n % 62)));
        }
        return s.toString();
    }

    private static long decoding(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        long n = 0;
        for (int i = 0; i < s.length(); i++) {
            n += BASE62_ALPHABET.indexOf(s.charAt(i)) * Math.pow(62, i);
        }
        return n;
    }
}
