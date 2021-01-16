package com.demo.controller;

import com.demo.annotation.Auth;
import com.demo.constant.Constant;
import com.demo.constant.RedisConstant;
import com.demo.entity.pojo.Result;
import com.demo.service.ThirdLoginService;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @Auth
    @PostMapping("/qq")
    public Result qq() {
        String uri = "https://graph.qq.com/oauth2.0/authorize" + //
                "?response_type=code" + //
                "&client_id=%s" + //
                "&redirect_uri=%s" + //
                "&state=%s" + //
                "&scope=get_user_info";
        String redisSign = AuthUtils.getSign(request);
        String qqState = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, 32);
        String state = redisSign + "_" + qqState;
        RedisUtils.hashSet(redisSign, RedisConstant.QQ_STATE_NAME, qqState);
        return Result.o(String.format(uri, Constant.QQ_APP_ID, Constant.QQ_CALLBACK_URL, state));
    }

    /**
     * qq回调<br>
     * http://404z.cn:8080/callback/qq/login?code=AD854A2B9B952018E78D4573FA3B57A1&state=123_abc
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
        String redisSign = stateSpilt[0];
        String qqState = stateSpilt[1];
        if (StringUtils.existEmpty(redisSign, qqState)) {
            return Result.e1();
        }
        return thirdLoginService.qqCallback(redisSign, code, qqState);
    }

}
