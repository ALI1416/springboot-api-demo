package com.demo.util;

import me.ihxq.projects.pna.PhoneNumberInfo;
import me.ihxq.projects.pna.PhoneNumberLookup;

/**
 * <h1>电话工具</h1>
 *
 * <p>
 * createDate 2020/11/22 16:15:07
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class PhoneUtils {

    /**
     * PhoneNumberLookup实例
     */
    private final static PhoneNumberLookup PHONE_NUMBER_LOOKUP = new PhoneNumberLookup();

    public static void main(String[] args) {
        System.out.println(getPhoneInfo("18754710000"));
    }

    /**
     * 获取手机号码信息
     *
     * @param number 手机号码
     * @return 错误：attribution=null, isp=null
     * @see me.ihxq.projects.pna.PhoneNumberLookup#lookup(String phoneNumber)
     */
    public static PhoneNumberInfo getPhoneInfo(String number) {
        return PHONE_NUMBER_LOOKUP.lookup(number).orElseGet(() -> new PhoneNumberInfo(number, null, null));
    }

}
