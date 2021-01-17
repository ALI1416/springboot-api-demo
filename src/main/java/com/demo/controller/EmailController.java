package com.demo.controller;

import com.demo.annotation.Auth;
import com.demo.constant.CaptchaTypeEnum;
import com.demo.constant.RedisConstant;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.UserVo;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.demo.util.MailUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>邮件api</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RequestMapping("/email")
@RestController
public class EmailController {

    /**
     * 发送邮件
     */
    @Auth(skipLogin = true)
    @PostMapping(value = {"", "/", "index"})
    public Result index(HttpServletRequest request, @RequestBody UserVo user) {
        if (StringUtils.existEmpty(user.getEmail(), user.getCaptcha())) {
            return Result.e1();
        }
        /* 判断验证码是否正确 */
        Boolean correct = AuthUtils.correctCaptcha(request, CaptchaTypeEnum.EMAIL, user.getCaptcha());
        // 验证码过期
        if (correct == null) {
            return Result.e(ResultCodeEnum.CAPTCHA_IS_EXPIRED);
        }
        // 验证码错误
        if (!correct) {
            return Result.e(ResultCodeEnum.CAPTCHA_ERROR);
        }
        // 生成邮件验证码
        String captcha = StringUtils.getRandom(StringUtils.NUMBER_UPPER_LETTER, 8);
        AuthUtils.setEmailCaptcha(request, captcha);
        MailUtils.sendMail(user.getEmail(), "注册验证码", "您的验证码为：" + captcha);
        return Result.o();
    }

    @GetMapping("text")
    public String text() {
        MailUtils.sendMail("1416978277@qq.com", "主题", "内容");
        return "ok";
    }

    @GetMapping("html")
    public String html() {
        MailUtils.sendMailHtml("1416978277@qq.com", "主题Html", "<h1>内容Html</h1>");
        return "ok";
    }

}
