package com.demo.util;

import com.demo.constant.Ip2RegionConstant;
import com.demo.entity.pojo.Ip;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * <h1>IP工具</h1>
 *
 * <p>
 * createDate 2020/11/18 10:27:10
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Component
public class IpUtils {

    public static void main(String[] args) {
        String ip = "202.108.22.5";
        // String ip = "255.255.255.255";
        // String ip = "0.0.0.0";
        System.out.println(ip);

        boolean isIp = isIp(ip);
        System.out.println(isIp);

        long longIp = ip2Long(ip);
        System.out.println(longIp);

        boolean isIp2 = isIp(longIp);
        System.out.println(isIp2);

        String strIp = long2Ip(longIp);
        System.out.println(strIp);

        ip2RegionInitial();
        System.out.println(getIpInfo("157.122.178.42"));
        System.out.println(getIpInfo(null));
    }

    /**
     * IP地址正则表达式
     */
    private final static String IP_PATTERN = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]" +
            "|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
    /**
     * ip2region搜索实例
     */
    private static DbSearcher ip2regionSearcher = null;

    /**
     * 初始化DbSearcher实例
     *
     * @see org.lionsoul.ip2region.DbConfig#DbConfig()
     * @see org.lionsoul.ip2region.DbSearcher#DbSearcher(DbConfig dbConfig, String dbFile)
     */
    public static void ip2RegionInitial() {
        if (ip2regionSearcher == null) {
            synchronized (IpUtils.class) {
                if (ip2regionSearcher == null) {
                    try {
                        ip2regionSearcher = new DbSearcher(new DbConfig(), Ip2RegionConstant.REFERENCE_PATH);
                        // ip2regionSearcher = new DbSearcher(new DbConfig(), "D:/springboot-api-demo/ip2region/data
                        // .db");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获取IP信息
     *
     * @param ip IP地址
     * @return 错误：属性全为null
     * @see org.lionsoul.ip2region.DbSearcher#memorySearch(String ip)
     */
    public static Ip getIpInfo(String ip) {
        DataBlock block = null;
        try {
            block = ip2regionSearcher.memorySearch(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (block != null) {
            return new Ip(block.getRegion());
        } else {
            return new Ip();
        }
    }

    /**
     * 是字符串IP地址
     *
     * @param str 字符串IP地址
     * @see java.util.regex.Pattern#matches(String regex, CharSequence input)
     * @see #IP_PATTERN
     */
    public static boolean isIp(String str) {
        return Pattern.matches(IP_PATTERN, str);
    }

    /**
     * 是数字IP地址
     *
     * @param n 数字IP地址
     */
    public static boolean isIp(long n) {
        return (n > -1) && (n < (1L << 32));
    }

    /**
     * IP地址字符串转数字
     *
     * @param str 字符串IP地址
     */
    public static long ip2Long(String str) {
        String[] s = str.split("\\.");
        return (Long.parseLong(s[0]) << 24) | (Integer.parseInt(s[1]) << 16) | (Integer.parseInt(s[2]) << 8) | Integer.parseInt(s[3]);
    }

    /**
     * IP地址数字转字符串
     *
     * @param n 数字IP地址
     */
    public static String long2Ip(long n) {
        return (n >> 24) + "." + (n >> 16 & 0xFF) + "." + (n >> 8 & 0xFF) + "." + (n & 0xFF);
    }
}
