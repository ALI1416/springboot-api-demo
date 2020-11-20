package com.demo.property.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <h1>词典文件配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@ConfigurationProperties(prefix = "ansj", ignoreInvalidFields = true)
@Component
@Data
public class AnsjDefaultYml {
    private String folderPath;
}
