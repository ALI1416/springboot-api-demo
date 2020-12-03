package com.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import com.demo.entity.User;
import com.demo.tool.PropertySourceLoadYamlFactory;

import java.time.Duration;
import java.util.List;

/**
 * <h1>My配置</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
//加载顺序为从左到右顺序加载，后加载的会覆盖先加载的属性值。
//@PropertySource(value = { "classpath:a.yml", "classpath:b.yml" }, factory = PropertySourceLoadYamlFactory.class)
//@PropertySource读取外部配置文件。因为只能读取properties类型，所以需要使用PropertySourceLoadYamlFactory将yaml文件转换为properties
@PropertySource(value = {"classpath:my.yml"}, factory = PropertySourceLoadYamlFactory.class)
// prefix前缀。如果是读取所有配置，可以不写prefix
// ignoreInvalidFields = true忽略类型不匹配或不存在的字段，没有指定默认值的会自动赋值(基本类型：默认值；包装类型：null)
// ignoreUnknownFields = false配置中有不能绑定的字段，会报错(ignoreInvalidFields = true时无效)
@ConfigurationProperties(prefix = "test", ignoreInvalidFields = true)
@Component
@Data
public class MyYml {
    /**
     * 没有指定默认值，为该类型的默认值
     */
    private boolean booleanType;
    /**
     * 指定默认值
     */
    private int intType = 8;
    private long longType;
    private float floatType;
    private double doubleType;
    private String stringType;
    private User userType;
    private int[] intsType;
    private List<String> stringListType;
    private List<User> userListType;
    /**
     * 持续时间
     */
    private Duration durationType;
    /**
     * 储存容量
     */
    private DataSize dataSizeType;
}
