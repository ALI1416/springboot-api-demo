package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wf.captcha.SpecCaptcha;

@Controller
public class CaptchaController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 设置请求头为输出图片类型 */
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        /* 验证码类型 */
        // png类型
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 5);
        // gif类型
//        GifCaptcha captcha = new GifCaptcha(130, 48, 5);
        // 中文png类型
//         ChineseCaptcha captcha = new ChineseCaptcha(130, 48, 2);
        // 中文gif类型
//         ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48, 2);
        // 算术类型
//         ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48, 2);

        /* 其他设置 */
        // 设置字体
//        captcha.setFont(new Font("Verdana", Font.PLAIN, 32));
//        captcha.setFont(Captcha.FONT_1);
        // 设置类型，纯数字、纯字母、字母数字混合
//        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
//        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);

        /* 结果 */
        // 获取运算的公式：3+2=?
//         System.out.println(captcha.getArithmeticString());
        // 获取验证码字符或运算结果
        System.out.println(captcha.text());

        /* 储存 */
        // 验证码存入session
//         request.getSession().setAttribute("captcha",captcha.text().toLowerCase());

        /* 输出 */
        captcha.out(response.getOutputStream());
    }
}
