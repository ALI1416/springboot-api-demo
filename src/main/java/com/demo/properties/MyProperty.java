package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import com.demo.factory.PropertySourceLoadYamlFactory;
import com.demo.po.User;

import java.time.Duration;
import java.util.List;

/**
 * <h1>My配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
//@PropertySource读取外部配置文件。因为只能读取properties类型，所以需要使用PropertySourceLoadYamlFactory将yaml文件转换为properties
@PropertySource(value = { "classpath:my.yml" }, factory = PropertySourceLoadYamlFactory.class)
// prefix前缀
// ignoreInvalidFields = true忽略类型不匹配或不存在的字段，没有指定默认值的会自动赋值(基本类型：默认值；包装类型：null)
// ignoreUnknownFields = false配置中有不能绑定的字段，会报错(ignoreInvalidFields = true时无效)
@ConfigurationProperties(prefix = "test", ignoreInvalidFields = true)
@Component
@Data
public class MyProperty {
    private boolean booleanType;
    private int intType = 4;
    private long longType;
    private float floatType;
    private double doubleType;
    private String stringType;
    private User userType;
    private int[] intsType;
    private List<String> stringListType;
    private List<User> userListType;
    // 持续时间
    private Duration durationType;
    // 储存容量
    private DataSize dataSizeType;
}
