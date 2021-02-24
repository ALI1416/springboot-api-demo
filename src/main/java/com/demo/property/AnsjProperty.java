package com.demo.property;

/**
 * <h1>Ansj属性</h1>
 *
 * <p>
 * createDate 2020/11/21 09:29:16
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class AnsjProperty {

    private final static LibraryProperties LIBRARY = Properties.libraryProperties;
    /**
     * 自定义词典文件引用路径(本机绝对地址)
     */
    public final static String DEFAULT_REFERENCE_PATH = LIBRARY.getDic();
    /**
     * 歧义词典文件引用路径(本机绝对地址)
     */
    public final static String AMBIGUITY_REFERENCE_PATH = LIBRARY.getAmbiguity();
    private final static AnsjAppYml ANSJ = Yml.ansjAppYml;
    /**
     * 自定义词典文件在本项目中resources文件夹下的路径
     */
    public final static String DEFAULT_RESOURCE_PATH = ANSJ.getDefaultResourcePath();
    /**
     * 歧义词典文件在本项目中resources文件夹下的路径
     */
    public final static String AMBIGUITY_RESOURCE_PATH = ANSJ.getAmbiguityResourcePath();

}
