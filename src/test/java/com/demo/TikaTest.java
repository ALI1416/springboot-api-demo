package com.demo;

import org.apache.tika.Tika;
import org.apache.tika.detect.AutoDetectReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * <h1>TikaTest</h1>
 *
 * <p>
 * createDate 2021/02/17 12:37:36
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class TikaTest {

    /**
     * 文件类型
     */
    @Test
    void detect() {
        try {
            String path = "E:\\Desktop\\mime.types.txt";
            File file = new File(path);
            Tika tika = new Tika();
            System.out.println(tika.detect(file));
            FileInputStream fileInputStream = new FileInputStream(file);
            AutoDetectReader autoDetectReader = new AutoDetectReader(fileInputStream);
            System.out.println(autoDetectReader.getCharset().name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
