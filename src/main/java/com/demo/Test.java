package com.demo;

import java.sql.Timestamp;

import com.demo.constant.CaptchaTypeEnum;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.entity.pojo.ResultBatch;
import com.demo.entity.vo.UserVo;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * <h1>测试类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Test {

    public static void main(String[] args) {

        User u = new User();
        u.setId(0L);
        u.setAccount("root");
        u.setName("root");
        u.setGender(0);
        u.setYear(0);
        u.setCreateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(u);

        UserVo uv = new UserVo();
        uv.setId(1L);
        uv.setAccount("admin");
        uv.setName("ck");
        uv.setGender(1);
        uv.setYear(1998);
        uv.setYearNot(0);
        uv.setYearEnd(2000);
        System.out.println(uv);

        Result r1 = Result.o(ResultCodeEnum.OK);
        System.out.println(r1.isOk());
        Result r2 = Result.e(ResultCodeEnum.ERROR);
        System.out.println(r2.isOk());
        Result r3 = Result.e(ResultCodeEnum.USER_ACCOUNT_FORBIDDEN, "哈哈哈哈", u);
        System.out.println(r3);
        User ru = (User) r3.getData();
        System.out.println(ru);

        ResultBatch<User> batchResult = new ResultBatch<>();
        batchResult.add(true, u, "ok");
        batchResult.add(false, u, "fff");
        batchResult.add(true, u, "true");
        batchResult.add(false, u, "aaa");
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());
        ResultBatch<User> batchResult2 = new ResultBatch<>();
        batchResult2.add(false, u, "false");
        batchResult2.add(true, u, "qwqw");
        batchResult2.add(false, u, "fet");
        ResultBatch<User> batchResultMerge = ResultBatch.merge(batchResult, batchResult2);
        System.out.println(batchResultMerge);

        // google chrome
        // String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
        // AppleWebKit/537.36 (KHTML, like
        // Gecko) Chrome/72.0.3626.119 Safari/537.36";
        // ie9
        String userAgentString = "User-Agent:Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;";
        // safari
        // String userAgentString = "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS
        // X 10_6_8; en-us)
        // AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50";
        // iphone
        // String userAgentString = "User-Agent:Mozilla/5.0 (iPhone; U; CPU iPhone OS
        // 4_3_3 like Mac OS X;
        // en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2
        // Safari/6533.18.5";
        // ipad
        // String userAgentString = "User-Agent:Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like
        // Mac OS X; en-us)
        // AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2
        // Safari/6533.18.5";
        // android
        // String userAgentString = "User-Agent: MQQBrowser/26 Mozilla/5.0 (Linux; U;
        // Android 2.3.7; zh-cn;
        // MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko)
        // Version/4.0 Mobile
        // Safari/533.1";
        // windows phone
        // String userAgentString = "User-Agent: Mozilla/5.0 (compatible; MSIE 9.0;
        // Windows Phone OS 7.5;
        // Trident/5.0; IEMobile/9.0; HTC; Titan)";
        UserAgent userAgent = new UserAgent(userAgentString);
        System.out.println(userAgent.getBrowser().getName());
        System.out.println(userAgent.getBrowserVersion());
        System.out.println(userAgent.getOperatingSystem().getName());
        System.out.println(userAgent.getOperatingSystem().getDeviceType().getName());

        System.out.println(CaptchaTypeEnum.findByType(1));

    }

}
