package com.demo.constant;

import java.time.Duration;
import java.util.List;

import org.springframework.util.unit.DataSize;

import com.demo.po.User;
import com.demo.properties.MyProperty;
import com.demo.properties.Properties;

/**
 * <h1>My常量类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
public class MyConstant {

    /**
     * 导入配置类
     */
    private static MyProperty myProperty = Properties.myProperty;

    /**
     * 常量A
     */
    public final static int A = myProperty.getIntType();
    /**
     * 常量B
     */
    public final static User B = myProperty.getUserType();
    /**
     * 常量C
     */
    public final static List<User> C = myProperty.getUserListType();
    /**
     * 常量D
     */
    public final static Duration D = myProperty.getDurationType();
    /**
     * 常量E
     */
    public final static DataSize E = myProperty.getDataSizeType();
}
