package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.Constant;
import com.demo.constant.RedisConstant;
import com.demo.constant.ThirdLoginTypeEnum;
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

    /**
     * 获取qq登录链接<br>
     * 包括使用qq登录/注册/绑定账号<br>
     * 
     * @param type 登录类型：0、登录/注册，1绑定
     * @see com.demo.constant.ThirdLoginTypeEnum
     */
    @Auth(skipLogin = true)
    @PostMapping("/qq/{type}")
    public Result qq(@PathVariable int type) {
        if (ThirdLoginTypeEnum.findByType(type) == null) {
            return Result.e1();
        }
        // 除登录以外，都需要登录账号
        if (type != ThirdLoginTypeEnum.LOGIN.getType()) {
            if (AuthUtils.getUserId(request) == null) {
                return Result.e2();
            }
        }
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
        String state = sign + "_" + qqState + "_" + type;
        RedisUtils.hashSet(name, RedisConstant.QQ_STATE__STATE, qqState, RedisConstant.QQ_STATE_EXPIRE);
        RedisUtils.hashSet(name, RedisConstant.QQ_STATE__TYPE, type);
        return Result.o(String.format(url, Constant.QQ_APP_ID, Constant.QQ_CALLBACK_URL, state));
    }

    /**
     * qq回调<br>
     * http://404z.cn:8080/thirdLogin/qq/callback?code=123abc&state=123_abc_0
     */
    @GetMapping("/qq/callback")
    public Result qqCallback(String code, String state) {
        if (StringUtils.existEmpty(code, state)) {
            return Result.e1();
        }
        String[] stateSpilt = state.split("_");
        if (stateSpilt.length != 3) {
            return Result.e1();
        }
        String sign = stateSpilt[0];
        String qqState = stateSpilt[1];
        String typeString = stateSpilt[2];
        if (StringUtils.existEmpty(sign, qqState, typeString)) {
            return Result.e1();
        }
        int type = Integer.valueOf(typeString);
        if (ThirdLoginTypeEnum.findByType(type) == null) {
            return Result.e1();
        }
        return thirdLoginService.qqCallback(request, sign, code, qqState, type);
    }

}
