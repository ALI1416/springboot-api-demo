package com.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <h1>ansj词典配置</h1>
 *
 * <p>
 * createDate 2020/11/21 10:25:05
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "ansj", ignoreInvalidFields = true)
@Component
@Data
public class AnsjAppYml {
    private String defaultResourcePath = "/file/ansj/default.dic";
    private String ambiguityResourcePath = "/file/ansj/ambiguity.dic";
}
