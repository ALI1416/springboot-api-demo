package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.mail", ignoreInvalidFields = true)
@Component
@Data
public class MailProperty {
    private String host;
    private String username;
    private String password;
    private String protocol;
    private String defaultEncoding;
}
