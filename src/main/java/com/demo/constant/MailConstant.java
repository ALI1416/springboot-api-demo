package com.demo.constant;

import com.demo.property.MailAppYml;
import com.demo.property.Yml;

/**
 * <h1>邮件常量类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class MailConstant {

    private static final MailAppYml MAIL = Yml.mailAppYml;

    /**
     * 主机
     */
    public final static String HOST = MAIL.getHost();
    /**
     * 用户名
     */
    public final static String USERNAME = MAIL.getUsername();
    /**
     * 密码
     */
    public final static String PASSWORD = MAIL.getPassword();
    /**
     * 协议
     */
    public final static String PROTOCOL = MAIL.getProtocol();
    /**
     * 字符集
     */
    public final static String DEFAULT_ENCODING = MAIL.getDefaultEncoding();
}
