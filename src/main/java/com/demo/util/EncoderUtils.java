package com.demo.util;

import com.demo.source.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.CRC32;

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

        String sha1Password = EncoderUtils.sha1(rawPassword);
        System.out.println(sha1Password);

        String crc32Password = EncoderUtils.crc32(rawPassword);
        System.out.println(crc32Password);

        String bCryptEncodePassword = EncoderUtils.bCryptEncode(rawPassword);
        System.out.println(bCryptEncodePassword);

        boolean bCryptMatchesResult = EncoderUtils.bCryptMatches(rawPassword, bCryptEncodePassword);
        System.out.println(bCryptMatchesResult);

        String base64Password = EncoderUtils.base64Encoder(rawPassword);
        System.out.println(base64Password);

        String base64DecodePassword = EncoderUtils.base64Decoder(base64Password);
        System.out.println(base64DecodePassword);

        long base62RawPassword = 1234567890123456789L;
        System.out.println(base62RawPassword);

        String base62EncoderPassword = EncoderUtils.base62Encoder(base62RawPassword);
        System.out.println(base62EncoderPassword);

        long base62DecoderPassword = EncoderUtils.base62Decoder(base62EncoderPassword);
        System.out.println(base62DecoderPassword);

    }

    /**
     * UTF8字符集编码
     */
    private final static Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * base62字母表
     */
    private final static String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * MD5加密
     *
     * @param rawPassword 原始数据
     */
    public static String md5(String rawPassword) {
        return DigestUtils.md5Hex(rawPassword);
    }

    /**
     * SHA1加密
     *
     * @param rawPassword 原始数据
     */
    public static String sha1(String rawPassword) {
        return DigestUtils.sha1Hex(rawPassword);
    }

    /**
     * CRC32加密
     *
     * @param rawPassword 原始数据
     */
    public static String crc32(String rawPassword) {
        byte[] b = rawPassword.getBytes();
        CRC32 c = new CRC32();
        c.reset();
        c.update(b, 0, b.length);
        return Long.toHexString(c.getValue());
    }

    /**
     * BCrypt加密
     *
     * @param rawPassword 原始数据
     */
    public static String bCryptEncode(CharSequence rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    /**
     * BCrypt验证
     *
     * @param rawPassword     原始数据
     * @param encodedPassword 加密后的数据
     */
    public static boolean bCryptMatches(CharSequence rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
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
     * base62编码
     *
     * @param n 原始数据
     */
    public static String base62Encoder(long n) {
        if (n < 1) {
            return "0";
        }
        StringBuilder s = new StringBuilder();
        for (; n > 0; n /= 62) {
            s.append(BASE62_ALPHABET.charAt((int) (n % 62)));
        }
        return s.reverse().toString();
    }

    /**
     * base62编码2<br>
     * 此方法速度较慢，不推荐使用
     *
     * @param n 原始数据
     * @see #base62Encoder(long n)
     */
    public static String base62Encoder2(long n) {
        if (n < 1) {
            return "0";
        }
        StringBuilder s = new StringBuilder();
        long a;
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
     * base62解码
     *
     * @param s 加密后的数据
     */
    public static long base62Decoder(String s) {
        if (s == null) {
            return 0;
        }
        int l = s.length();
        if (s.length() == 0) {
            return 0;
        }
        long n = 0;
        long p = 1;
        int c;
        for (int i = l - 1; i >= 0; i--, p *= 62) {
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

    /**
     * base62解码2<br>
     * 此方法速度较慢，不推荐使用
     *
     * @param s 加密后的数据
     * @see #base62Decoder(String s)
     */
    public static long base62Decoder2(String s) {
        if (s == null) {
            return 0;
        }
        int l = s.length();
        if (l == 0) {
            return 0;
        }
        long n = 0;
        long p = 1;
        for (int i = 0; i < l; i++, p *= 62) {
            n += BASE62_ALPHABET.indexOf(s.charAt(l - i - 1)) * p;
        }
        return n;
    }
}
