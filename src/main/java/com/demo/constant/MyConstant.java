package com.demo.constant;

import java.time.Duration;
import java.util.List;

import org.springframework.util.unit.DataSize;

import com.demo.entity.po.User;
import com.demo.property.MyYml;
import com.demo.property.Yml;

/**
 * <h1>My常量</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class MyConstant {

    private static final MyYml MY_YML = Yml.myYml;

    /**
     * 常量A
     */
    public final static int A = MY_YML.getIntType();
    /**
     * 常量B
     */
    public final static User B = MY_YML.getUserType();
    /**
     * 常量C
     */
    public final static List<User> C = MY_YML.getUserListType();
    /**
     * 常量D
     */
    public final static Duration D = MY_YML.getDurationType();
    /**
     * 常量E
     */
    public final static DataSize E = MY_YML.getDataSizeType();
}
