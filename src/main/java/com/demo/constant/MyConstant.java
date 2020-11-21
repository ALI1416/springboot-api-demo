package com.demo.constant;

import java.time.Duration;
import java.util.List;

import org.springframework.util.unit.DataSize;

import com.demo.po.User;
import com.demo.property.MyYml;
import com.demo.property.Yml;

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

    private static MyYml myYml = Yml.myYml;

    /**
     * 常量A
     */
    public final static int A = myYml.getIntType();
    /**
     * 常量B
     */
    public final static User B = myYml.getUserType();
    /**
     * 常量C
     */
    public final static List<User> C = myYml.getUserListType();
    /**
     * 常量D
     */
    public final static Duration D = myYml.getDurationType();
    /**
     * 常量E
     */
    public final static DataSize E = myYml.getDataSizeType();
}
