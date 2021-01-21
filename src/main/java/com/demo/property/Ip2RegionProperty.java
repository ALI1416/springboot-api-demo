package com.demo.property;

/**
 * <h1>ip2region属性</h1>
 *
 * <p>
 * createDate 2020/11/21 10:27:45
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Ip2RegionProperty {

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
