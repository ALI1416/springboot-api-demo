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

	// 导入配置类
	private final static MyProperty myProperty = Properties.myProperty;

	// 定义常量
	public final static int A = myProperty.getIntType();
	public final static User B = myProperty.getUserType();
	public final static List<User> C = myProperty.getUserListType();
	public final static Duration D = myProperty.getDurationType();
	public final static DataSize E = myProperty.getDataSizeType();
}
