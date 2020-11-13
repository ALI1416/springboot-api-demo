package com.demo.constant;

import com.demo.properties.MailProperty;
import com.demo.properties.Properties;

/**
 * <h1>邮件常量类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
public class MailConstant {

    /**
     * 导入配置类
     */
    private static MailProperty mailProperty = Properties.mailProperty;

    /**
     * 主机
     */
    public final static String HOST = mailProperty.getHost();
    /**
     * 用户名
     */
    public final static String USERNAME = mailProperty.getUsername();
    /**
     * 密码
     */
    public final static String PASSWORD = mailProperty.getPassword();
    /**
     * 协议
     */
    public final static String PROTOCOL = mailProperty.getProtocol();
    /**
     * 字符集
     */
    public final static String DEFAULT_ENCODING = mailProperty.getDefaultEncoding();
}
