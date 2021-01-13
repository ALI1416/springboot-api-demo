package com.demo.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>客户端信息工具类</h1>
 *
 * <p>
 * 获取客户端的信息
 * </p>
 *
 * <p>
 * createDate 2021/01/12 21:29:25
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class ClientInfoUtils {

    /**
     * 获取ip
     *
     * @param request HttpServletRequest
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                // 根据网卡获取本机配置的IP
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (Exception e) {
                }
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = "0.0.0.0";
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (ip != null && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

}
