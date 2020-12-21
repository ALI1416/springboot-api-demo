package com.demo.task;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.demo.constant.Ip2RegionConstant;
import com.demo.util.FileUtils;
import com.demo.util.IpUtils;

/**
 * <h1>ip2region任务</h1>
 *
 * <p>
 * createDate 2020/11/20 19:42:59
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Ip2RegionTask {

    private final static Logger logger = LoggerFactory.getLogger(Ip2RegionTask.class);

    public static void main(String[] args) {
        initial();
    }

    public static void initial() {
        logger.info("Ip2Region初始化任务开始...");
        copyFile();
        IpUtils.ip2RegionInitial();
        start();
        logger.info("Ip2Region初始化任务结束。");
    }

    private static void copyFile() {
        try {
            logger.info("Ip2Region检查并创建文件...");
            // 文件夹
            File libraryPath = new File(Ip2RegionConstant.REFERENCE_PATH.substring(0,
                    Ip2RegionConstant.REFERENCE_PATH.lastIndexOf("/") + 1));
            if (!libraryPath.exists()) {
                if (libraryPath.mkdirs()) {
                    logger.info("Ip2Region文件夹缺失，创建在{}", libraryPath.getPath());
                } else {
                    logger.error("Ip2Region文件夹缺失，在{}创建失败！", libraryPath.getPath());
                }
            } else {
                logger.info("Ip2Region文件夹已存在，在{}", libraryPath.getPath());
            }
            // 数据文件
            File libraryDefaultFile = new File(Ip2RegionConstant.REFERENCE_PATH);
            if (!libraryDefaultFile.exists()) {
                ClassPathResource resource = new ClassPathResource(Ip2RegionConstant.RESOURCE_PATH);
                InputStream inputStream = resource.getInputStream();
                FileUtils.inputStream2File(inputStream, libraryDefaultFile.getPath());
                logger.info("Ip2Region数据文件缺失，创建在{}", libraryDefaultFile.getPath());
            } else {
                logger.info("Ip2Region数据文件已存在，在{}", libraryDefaultFile.getPath());
            }
            logger.info("Ip2Region检查并创建文件结束。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() {
        logger.info("Ip2Region开始加载...");
        logger.info("Ip2Region测试：" + IpUtils.getIpInfo("202.108.22.5"));
        logger.info("Ip2Region加载结束。");
    }

}
