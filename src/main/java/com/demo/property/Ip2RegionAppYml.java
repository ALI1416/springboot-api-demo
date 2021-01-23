package com.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h1>ip2region数据文件配置</h1>
 *
 * <p>
 * createDate 2020/11/21 10:24:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "ip2region", ignoreInvalidFields = true)
@Component
@Data
public class Ip2RegionAppYml {
    private String resourcePath = "/file/ip2region/data.db";
    private String referencePath = "D:/springboot-api-demo/ip2region/data.db";
}
