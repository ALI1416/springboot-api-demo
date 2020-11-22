package com.demo.constant;

import com.demo.property.LibraryProperties;
import com.demo.property.Properties;
import com.demo.property.Yml;
import com.demo.property.yml.AnsjDefaultYml;

/**
 * <h1>Ansj常量类</h1>
 *
 * <p>
 * createDate 2020/11/21 09:29:16
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class AnsjConstant {

    private static final LibraryProperties LIBRARY = Properties.libraryProperties;
    private static final AnsjDefaultYml ANSJ = Yml.ansjDefaultYml;

    /**
     * 自定义词典文件在本项目中resources文件夹下的路径
     */
    public static final String DEFAULT_RESOURCE_PATH = ANSJ.getDefaultResourcePath();
    /**
     * 歧义词典文件在本项目中resources文件夹下的路径
     */
    public static final String AMBIGUITY_RESOURCE_PATH = ANSJ.getAmbiguityResourcePath();
    /**
     * 自定义词典文件引用路径(本机绝对地址)
     */
    public static final String DEFAULT_REFERENCE_PATH = LIBRARY.getDic();
    /**
     * 歧义词典文件引用路径(本机绝对地址)
     */
    public static final String AMBIGUITY_REFERENCE_PATH = LIBRARY.getAmbiguity();

}
