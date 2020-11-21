package com.demo.constant;

import com.demo.property.Yml;
import com.demo.property.yml.Ip2RegionDefaultYml;

/**
 * <h1>ip2region常量类</h1>
 *
 * <p>
 * createDate 2020/11/21 10:27:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
public class Ip2RegionConstant {

    private static Ip2RegionDefaultYml ip2Region = Yml.ip2RegionDefaultYml;

    /**
     * 数据文件在本项目中resources文件夹下的路径
     */
    public static final String RESOURCE_PATH = ip2Region.getResourcePath();
    /**
     * 数据文件引用路径(本机绝对地址)
     */
    public static final String REFERENCE_PATH = ip2Region.getReferencePath();

}
