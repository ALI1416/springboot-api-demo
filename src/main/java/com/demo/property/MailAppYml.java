package com.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h1>邮件配置</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.mail", ignoreInvalidFields = true)
@Component
@Data
public class MailAppYml {
    private String host;
    private String username;
    private String password;
    private String protocol;
    private String defaultEncoding;
}
