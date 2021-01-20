package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.Constant;
import com.demo.constant.RedisConstant;
import com.demo.entity.pojo.Result;
import com.demo.service.ThirdLoginService;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>第三方登录api</h1>
 *
 * <p>
 * createDate 2021/01/13 21:08:39
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("thirdLogin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ThirdLoginController {

    private final HttpServletRequest request;
    private final ThirdLoginService thirdLoginService;

    @Auth(skipLogin = true)
    @PostMapping("/qq")
    public Result qq() {
        String url = "https://graph.qq.com/oauth2.0/authorize" + //
                "?response_type=code" + //
                "&client_id=%s" + //
                "&redirect_uri=%s" + //
                "&state=%s" + //
                "&scope=get_user_info";
        String sign = AuthUtils.getSign(request);
        String name = sign + RedisConstant.QQ_STATE_SUFFIX;
        // 保存到redis的值
        String qqState = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, 32);
        // 传递给qq的值
        String state = sign + "_" + qqState;
        RedisUtils.set(name, qqState, RedisConstant.QQ_STATE_EXPIRE);
        return Result.o(String.format(url, Constant.QQ_APP_ID, Constant.QQ_CALLBACK_URL, state));
    }

    /**
     * qq回调<br>
     * http://404z.cn:8080/thirdLogin/qq/callback?code=123abc&state=123_abc
     */
    @GetMapping("/qq/callback")
    public Result qqCallback(String code, String state) {
        if (StringUtils.existEmpty(code, state)) {
            return Result.e1();
        }
        String[] stateSpilt = state.split("_");
        if (stateSpilt.length != 2) {
            return Result.e1();
        }
        String sign = stateSpilt[0];
        String qqState = stateSpilt[1];
        if (StringUtils.existEmpty(sign, qqState)) {
            return Result.e1();
        }
        return thirdLoginService.qqCallback(request, sign, code, qqState);
    }

}
