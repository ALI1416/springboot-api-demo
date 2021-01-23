package com.demo.init;

import com.demo.tool.Id;
import com.demo.util.PhoneUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>总初始化</h1>
 *
 * <p>
 * createDate 2020/11/21 09:28:54
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Slf4j
public class Init {

    public static void init() {
        log.info("初始化开始...");
        //        AnsjInit.init();
        Ip2RegionInit.init();
        //        phoneNumberLookupInit();
        idInit();
        log.info("初始化结束。");
    }

    /**
     * 手机号码信息任务
     */
    public static void phoneNumberLookupInit() {
        log.info("PhoneNumberLookup初始化开始...");
        log.info("PhoneNumberLookup开始加载...");
        log.info("PhoneNumberLookup测试：" + PhoneUtils.getPhoneInfo("18754710000"));
        log.info("PhoneNumberLookup加载结束。");
        log.info("PhoneNumberLookup初始化结束。");
    }

    /**
     * 高性能ID生成器任务
     */
    public static void idInit() {
        log.info("高性能Id生成器初始化开始...");
        log.info("高性能Id生成器开始加载...");
        log.info("高性能Id生成器测试：" + Id.next());
        log.info("高性能Id生成器加载结束。");
        log.info("高性能Id生成器初始化结束。");
    }


}
