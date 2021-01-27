package com.demo.controller.common;

import com.demo.annotation.Auth;
import com.demo.constant.RedisConstant;
import com.demo.entity.pojo.Result;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>权限认证api</h1>
 *
 * <p>
 * createDate 2020/12/05 19:39:35
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Auth
@RequestMapping("/auth")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final HttpServletRequest request;

    @Auth(skip = true)
    @PostMapping("getToken")
    public Result getToken() {
        long redisSign = Id.next();
        String token = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, 128);
        RedisUtils.hashSet(String.valueOf(redisSign), RedisConstant._TOKEN, token, RedisConstant.EXPIRE);
        Map<String, Object> map = new HashMap<>(2);
        map.put(RedisConstant.SIGN, redisSign);
        map.put(RedisConstant._TOKEN, token);
        return Result.o(map);
    }

    @PostMapping("login")
    public Long login() {
        String redisSign = AuthUtils.getSign(request);
        RedisUtils.hashSet(String.valueOf(redisSign), RedisConstant._USER_ID, 1L, RedisConstant.EXPIRE);
        return AuthUtils.getUserId(request);
    }

    @Auth(skipLogin = true)
    @PostMapping("login2")
    public String login2() {
        return "ok";
    }
}
