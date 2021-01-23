package com.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>ansj词典配置</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@PropertySource(value = {"classpath:library.properties"})
@ConfigurationProperties(ignoreInvalidFields = true)
@Component
@Data
public class LibraryProperties {
    private String dic = "/file/ansj/default.dic";
    private String ambiguity = "/file/ansj/ambiguity.dic";
}
