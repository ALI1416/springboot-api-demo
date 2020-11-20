package com.demo.task;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.demo.constant.AnsjConstant;
import com.demo.util.FileUtils;
import com.demo.util.StringUtils;

public class AnsjTask {
    private final static Logger logger = LoggerFactory.getLogger(AnsjTask.class);

    public static void initial() {
        logger.info("ansj初始化任务开始...");
        copyFile();
        start();
        logger.info("ansj初始化任务结束。");
    }

    private static void copyFile() {
        try {
            logger.info("检查并创建词典文件...");
            File libraryPath = new File(AnsjConstant.LIBRARY_PATH);
            File libraryAmbiguityFile = new File(AnsjConstant.LIBRARY_PATH + AnsjConstant.LIBRARY_AMBIGUITY_FILE);
            File libraryDefaultFile = new File(AnsjConstant.LIBRARY_PATH + AnsjConstant.LIBRARY_DEFAULT_FILE);
            if (!libraryPath.exists()) {
                libraryPath.mkdir();
                logger.info("词典文件夹缺失，创建在" + libraryPath.getPath());
            } else {
                logger.info("词典文件夹已存在，在" + libraryPath.getPath());
            }
            if (!libraryAmbiguityFile.exists()) {
                ClassPathResource ambiguityDicR = new ClassPathResource(
                        AnsjConstant.FOLDER_PATH + AnsjConstant.LIBRARY_AMBIGUITY_FILE);
                InputStream ambiguityDicI = ambiguityDicR.getInputStream();
                FileUtils.inputStream2File(ambiguityDicI, libraryAmbiguityFile.getPath());
                logger.info("歧义词典ambiguity.dic缺失，创建在" + libraryAmbiguityFile.getPath());
            } else {
                logger.info("歧义词典ambiguity.dic已存在，在" + libraryAmbiguityFile.getPath());
            }
            if (!libraryDefaultFile.exists()) {
                ClassPathResource defaultDicR = new ClassPathResource(
                        AnsjConstant.FOLDER_PATH + AnsjConstant.LIBRARY_DEFAULT_FILE);
                InputStream defaultDicI = defaultDicR.getInputStream();
                FileUtils.inputStream2File(defaultDicI, libraryDefaultFile.getPath());
                logger.info("自定义词典default.dic缺失，创建在" + libraryDefaultFile.getPath());
            } else {
                logger.info("自定义词典default.dic已存在，在" + libraryDefaultFile.getPath());
            }
            logger.info("检查并创建词典文件结束。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() {
        logger.info("ansj分词工具开始加载...");
        logger.info(StringUtils.getAnsj("ansj分词工具加载成功。"));
        logger.info("ansj分词工具加载结束。");
    }

}
