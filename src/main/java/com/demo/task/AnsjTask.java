package com.demo.task;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.demo.constant.AnsjConstant;
import com.demo.util.FileUtils;
import com.demo.util.StringUtils;

/**
 * <h1>Ansj任务类</h1>
 *
 * <p>
 * createDate 2020/11/20 19:42:59
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class AnsjTask {

    private final static Logger logger = LoggerFactory.getLogger(AnsjTask.class);

    public static void main(String[] args) {
        initial();
    }

    public static void initial() {
        logger.info("Ansj初始化任务开始...");
        copyFile();
        start();
        logger.info("Ansj初始化任务结束。");
    }

    private static void copyFile() {
        try {
            logger.info("Ansj检查并创建文件...");
            // 文件夹
            boolean hasFolder = true;
            File libraryPath = new File(AnsjConstant.DEFAULT_REFERENCE_PATH.substring(0, AnsjConstant.DEFAULT_REFERENCE_PATH.lastIndexOf("/") + 1));
            if (!libraryPath.exists()) {
                if (libraryPath.mkdirs()) {
                    logger.info("Ansj词典文件夹缺失，创建在{}", libraryPath.getPath());
                } else {
                    hasFolder = false;
                    logger.error("Ansj词典文件夹缺失，在{}创建失败！", libraryPath.getPath());
                }
            } else {
                logger.info("Ansj词典文件夹已存在，在{}", libraryPath.getPath());
            }
            if (hasFolder) {
                // 自定义词典
                File libraryDefaultFile = new File(AnsjConstant.DEFAULT_REFERENCE_PATH);
                if (!libraryDefaultFile.exists()) {
                    ClassPathResource resource = new ClassPathResource(AnsjConstant.DEFAULT_RESOURCE_PATH);
                    InputStream inputStream = resource.getInputStream();
                    FileUtils.inputStream2File(inputStream, libraryDefaultFile.getPath());
                    logger.info("Ansj自定义词典文件缺失，创建在{}", libraryDefaultFile.getPath());
                } else {
                    logger.info("Ansj自定义词典文件已存在，在{}", libraryDefaultFile.getPath());
                }
                // 歧义词典
                File libraryAmbiguityFile = new File(AnsjConstant.AMBIGUITY_REFERENCE_PATH);
                if (!libraryAmbiguityFile.exists()) {
                    ClassPathResource resource = new ClassPathResource(AnsjConstant.AMBIGUITY_RESOURCE_PATH);
                    InputStream inputStream = resource.getInputStream();
                    FileUtils.inputStream2File(inputStream, libraryAmbiguityFile.getPath());
                    logger.info("Ansj歧义词典文件缺失，创建在{}", libraryAmbiguityFile.getPath());
                } else {
                    logger.info("Ansj歧义词典文件已存在，在{}", libraryAmbiguityFile.getPath());
                }
            }
            logger.info("Ansj检查并创建词典文件结束。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() {
        logger.info("Ansj开始加载...");
        logger.info("Ansj测试：" + StringUtils.getAnsj("Ansj分词工具测试"));
        logger.info("Ansj加载结束。");
    }

}
