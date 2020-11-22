package com.demo.property.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <h1>ip2region数据文件配置类</h1>
 *
 * <p>
 * createDate 2020/11/21 10:24:53
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "ip2region")
@Component
@Data
public class Ip2RegionDefaultYml {
    private String resourcePath = "/file/ip2region/data.db";
    private String referencePath = "D:/springboot-api-demo/ip2region/data.db";
}
