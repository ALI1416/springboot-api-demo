package com.demo.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>总配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@Component
public class Properties {

    // 声明公共静态方法
    public static MyProperty myProperty;
    public static MailProperty mailProperty;

    // 自动注入静态方法
    @Autowired
    private Properties(MyProperty myProperty, MailProperty mailProperty) {
        Properties.myProperty = myProperty;
        Properties.mailProperty = mailProperty;
    }
}
