package com.demo.init;

import com.demo.property.AnsjProperty;
import com.demo.util.FileUtils;
import com.demo.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * <h1>Ansj初始化</h1>
 *
 * <p>
 * createDate 2020/11/20 19:42:59
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Slf4j
@Component
public class AnsjInit {

    public static void init() {
        log.info("Ansj初始化开始...");
        copyFile();
        start();
        log.info("Ansj初始化结束。");
    }

    private static void copyFile() {
        try {
            log.info("Ansj检查并创建文件...");
            /*文件夹*/
            boolean hasFolder = true;
            File libraryPath = new File(AnsjProperty.DEFAULT_REFERENCE_PATH.substring(0,
                    AnsjProperty.DEFAULT_REFERENCE_PATH.lastIndexOf("/") + 1));
            if (!libraryPath.exists()) {
                if (libraryPath.mkdirs()) {
                    log.info("Ansj词典文件夹缺失，创建在{}", libraryPath.getPath());
                } else {
                    hasFolder = false;
                    log.error("Ansj词典文件夹缺失，在{}创建失败！", libraryPath.getPath());
                }
            } else {
                log.info("Ansj词典文件夹已存在，在{}", libraryPath.getPath());
            }
            if (hasFolder) {
                /*自定义词典*/
                File libraryDefaultFile = new File(AnsjProperty.DEFAULT_REFERENCE_PATH);
                if (!libraryDefaultFile.exists()) {
                    ClassPathResource resource = new ClassPathResource(AnsjProperty.DEFAULT_RESOURCE_PATH);
                    InputStream inputStream = resource.getInputStream();
                    FileUtils.inputStream2File(inputStream, libraryDefaultFile.getPath());
                    log.info("Ansj自定义词典文件缺失，创建在{}", libraryDefaultFile.getPath());
                } else {
                    log.info("Ansj自定义词典文件已存在，在{}", libraryDefaultFile.getPath());
                }
                /*歧义词典*/
                File libraryAmbiguityFile = new File(AnsjProperty.AMBIGUITY_REFERENCE_PATH);
                if (!libraryAmbiguityFile.exists()) {
                    ClassPathResource resource = new ClassPathResource(AnsjProperty.AMBIGUITY_RESOURCE_PATH);
                    InputStream inputStream = resource.getInputStream();
                    FileUtils.inputStream2File(inputStream, libraryAmbiguityFile.getPath());
                    log.info("Ansj歧义词典文件缺失，创建在{}", libraryAmbiguityFile.getPath());
                } else {
                    log.info("Ansj歧义词典文件已存在，在{}", libraryAmbiguityFile.getPath());
                }
            }
            log.info("Ansj检查并创建词典文件结束。");
        } catch (Exception e) {
            log.error("复制文件时发生了错误！", e);
        }
    }

    private static void start() {
        log.info("Ansj开始加载...");
        log.info("Ansj测试：" + StringUtils.getAnsj("Ansj分词工具测试"));
        log.info("Ansj加载结束。");
    }

}
