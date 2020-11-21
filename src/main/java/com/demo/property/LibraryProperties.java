package com.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <h1>Ansj词典配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@PropertySource(value = { "classpath:library.properties" })
@ConfigurationProperties(ignoreInvalidFields = true)
@Component
@Data
public class LibraryProperties {
    private String dic = "D:/springboot-api-demo/ansj/default.dic";
    private String ambiguity = "D:/springboot-api-demo/ansj/ambiguity.dic";
}
