package com.demo.util;

import java.util.regex.Pattern;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.stereotype.Component;

/**
 * <h1>IP工具类</h1>
 *
 * <p>
 * createDate 2020/11/18 10:27:10
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
@Component
public class IpUtils {

    public static void main(String[] args) {
        String ip = "202.108.22.5";
//        String ip = "255.255.255.255";
//        String ip = "0.0.0.0";
        System.out.println(ip);

        boolean isIp = isIp(ip);
        System.out.println(isIp);

        long longIp = ip2Long(ip);
        System.out.println(longIp);

        boolean isIp2 = isIp(longIp);
        System.out.println(isIp2);

        String strIp = long2Ip(longIp);
        System.out.println(strIp);
        a(ip);
        a("157.122.178.42");
        a("8.8.8.8");
    }

    /**
     * IP地址正则表达式
     */
    private final static String IP_PATTERN = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
    /**
     * ip2region数据库路径
     * 最新版https://github.com/lionsoul2014/ip2region/raw/master/data/ip2region.db
     */
    private static String ip2regionDbPath = IpUtils.class.getResource("/ip2region.db").getPath();
    /**
     * ip2region配置
     */
    private static DbConfig ip2regionConfig = null;
    /**
     * ip2region搜索
     */
    private static DbSearcher ip2regionSearcher = null;
    static {
        try {
            ip2regionConfig = new DbConfig();
            ip2regionSearcher = new DbSearcher(ip2regionConfig, ip2regionDbPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String ip) {
        try {
            DataBlock block = ip2regionSearcher.btreeSearch(ip);
            System.out.println(block);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是字符串IP地址
     * 
     * @param str 字符串IP地址
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
        return (Long.parseLong(s[0]) << 24) | (Integer.parseInt(s[1]) << 16) | (Integer.parseInt(s[2]) << 8)
                | Integer.parseInt(s[3]);
    }

    /**
     * IP地址数字转字符串
     * 
     * @param n 数字IP地址
     */
    public static String long2Ip(long n) {
        return new StringBuilder().append(n >> 24).append('.').append(n >> 16 & 0xFF).append('.').append(n >> 8 & 0xFF)
                .append('.').append(n & 0xFF).toString();
    }
}
