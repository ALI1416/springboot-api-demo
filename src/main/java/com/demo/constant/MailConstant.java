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

	// 导入配置类
	private final static MailProperty mailProperty = Properties.mailProperty;

	// 定义常量
	public final static String HOST = mailProperty.getHost();
	public final static String USERNAME = mailProperty.getUsername();
	public final static String PASSWORD = mailProperty.getPassword();
	public final static String PROTOCOL = mailProperty.getProtocol();
	public final static String DEFAULT_ENCODING = mailProperty.getDefaultEncoding();
}
