package com.demo.constant;

import com.demo.property.Ip2RegionAppYml;
import com.demo.property.Yml;

/**
 * <h1>ip2region常量</h1>
 *
 * <p>
 * createDate 2020/11/21 10:27:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Ip2RegionConstant {

    private static final Ip2RegionAppYml IP_2_REGION = Yml.ip2RegionAppYml;

    /**
     * 数据文件在本项目中resources文件夹下的路径
     */
    public static final String RESOURCE_PATH = IP_2_REGION.getResourcePath();
    /**
     * 数据文件引用路径(本机绝对地址)
     */
    public static final String REFERENCE_PATH = IP_2_REGION.getReferencePath();

}
