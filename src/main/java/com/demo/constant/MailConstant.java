package com.demo.constant;

import com.demo.property.Yml;
import com.demo.property.yml.MailDefaultYml;

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
    private static MailDefaultYml mail = Yml.mailDefaultYml;

    /**
     * 主机
     */
    public final static String HOST = mail.getHost();
    /**
     * 用户名
     */
    public final static String USERNAME = mail.getUsername();
    /**
     * 密码
     */
    public final static String PASSWORD = mail.getPassword();
    /**
     * 协议
     */
    public final static String PROTOCOL = mail.getProtocol();
    /**
     * 字符集
     */
    public final static String DEFAULT_ENCODING = mail.getDefaultEncoding();
}
