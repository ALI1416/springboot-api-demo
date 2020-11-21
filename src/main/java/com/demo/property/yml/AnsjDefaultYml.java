package com.demo.property.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <h1>ansj词典文件配置类</h1>
 *
 * <p>
 * createDate 2020/11/21 10:25:05
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@ConfigurationProperties(prefix = "ansj")
@Component
@Data
public class AnsjDefaultYml {
    private String defaultResourcePath = "/file/ansj/default.dic";
    private String ambiguityResourcePath = "/file/ansj/ambiguity.dic";
}
