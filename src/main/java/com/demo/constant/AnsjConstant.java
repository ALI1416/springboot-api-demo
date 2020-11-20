package com.demo.constant;

import com.demo.property.LibraryProperties;
import com.demo.property.Properties;
import com.demo.property.Yml;
import com.demo.property.yml.AnsjDefaultYml;

public class AnsjConstant {
    /**
     * 导入配置类
     */
    private static LibraryProperties library = Properties.libraryProperties;
    private static AnsjDefaultYml ansj = Yml.ansjDefaultYml;

    /**
     * 词典文件在本项目中的路径
     */
    public static final String FOLDER_PATH = ansj.getFolderPath();

    /**
     * 词典文件夹路径
     */
    public static final String LIBRARY_PATH = library.getDic().substring(0, library.getDic().lastIndexOf("/") + 1);
    /**
     * 自定义词典文件名default.dic
     */
    public static final String LIBRARY_DEFAULT_FILE = library.getDic().substring(library.getDic().lastIndexOf("/") + 1);
    /**
     * 歧义词典文件名ambiguity.dic
     */
    public static final String LIBRARY_AMBIGUITY_FILE = library.getAmbiguity().substring(library.getAmbiguity().lastIndexOf("/") + 1);

}
