package com.demo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>Properties总配置类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
@Component
public class Properties {

    public static LibraryProperties libraryProperties;

    @Autowired
    private Properties(LibraryProperties libraryProperties) {
        Properties.libraryProperties = libraryProperties;
    }

}
