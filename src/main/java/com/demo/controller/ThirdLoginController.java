package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.annotation.Auth;
import com.demo.constant.Constant;
import com.demo.constant.RedisConstant;
import com.demo.util.AuthUtils;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>TODO QqController Title</h1>
 *
 * <p>
 * TODO QqController Description
 * </p>
 *
 * <p>
 * createDate 2021/01/13 21:08:39
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("thirdLogin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ThirdLoginController {

    /**
     * @param request HttpServletRequest
     */
    @Auth
    @PostMapping("/qq")
    public String qq(HttpServletRequest request) {
        String uri = "https://graph.qq.com/oauth2.0/authorize" +//
                "?response_type=code" +//
                "&client_id=%s" +//
                "&redirect_uri=%s" +//
                "&state=%s" +//
                "&scope=get_user_info";
        String redisId = AuthUtils.getRedisId(request);
        String random = StringUtils.getRandom(StringUtils.NUMBER_LOWER_LETTER, 32);
        String state = redisId + "_" + random;
        RedisUtils.hashSet(redisId, RedisConstant.QQ_STATE_NAME, state);
        return String.format(uri, Constant.QQ_APP_ID, Constant.QQ_CALLBACK_URL, state);
    }

    /**
     * qq回调<br>
     * http://404z.cn:8080/callback/qq/login?code=AD854A2B9B952018E78D4573FA3B57A1&state=123_abc
     */
    @GetMapping("/qq/callback")
    public String qqCallback(String code, String state) {
        if (!"123".equals(state)) {
            return "stateError";
        }
        String tokenObj = this.getToken(code);
        Map<String, Object> tokenMap = JSON.parseObject(tokenObj);
        String accessToken = (String) tokenMap.get("access_token");
        String openidObj = this.getOpenidAndUnionid(accessToken);
        Map<String, Object> openidMap = JSON.parseObject(openidObj);
        String openid = (String) openidMap.get("openid");
        String userInfoObj = this.getUserInfo(accessToken, openid);
        return userInfoObj;
    }

    /**
     * 获取token<br>
     * {<br>
     * "access_token":"D7BF7285C42531F41831A1819EE1A38F",<br>
     * "expires_in":"7776000",<br>
     * "refresh_token":"7D470F114079F49E222B31590408707F"<br>
     * }<br>
     */
    @GetMapping("/getToken")
    public String getToken(String code) {
        String uri = "https://graph.qq.com/oauth2.0/token" +//
                "?grant_type=authorization_code" +//
                "&client_id={appId}" +//
                "&client_secret={appKey}" +//
                "&code={code}" +//
                "&redirect_uri={callbackUrl}" +//
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("appId", Constant.QQ_APP_ID);
        params.put("appKey", Constant.QQ_APP_KEY);
        params.put("code", code);
        params.put("callbackUrl", Constant.QQ_CALLBACK_URL);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class, params);
        Map<String, Object> map = JSON.parseObject(result);
        String accessToken = (String) map.get("access_token");
        System.out.println(result);
        System.out.println(accessToken);
        return result;
    }

    /**
     * 获取openid和unionid<br>
     * {<br>
     * "client_id":"101925994",<br>
     * "openid":"91449D4BB19893F674C07B111C1BB4FB",<br>
     * "unionid":"UID_3C6246E641847F77CF90BA17D543F497"<br>
     * }<br>
     */
    @GetMapping("/getOpenidAndUnionid")
    public String getOpenidAndUnionid(String accessToken) {
        String uri = "https://graph.qq.com/oauth2.0/me" +//
                "?access_token={accessToken}" +//
                "&unionid=1" +//
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class, params);
        Map<String, Object> map = JSON.parseObject(result);
        String openid = (String) map.get("openid");
        String unionid = (String) map.get("unionid");
        System.out.println(result);
        System.out.println(openid);
        System.out.println(unionid);
        return result;
    }

    /**
     * 获取用户信息<br>
     * {<br>
     * "ret": 0,<br>
     * "msg": "",<br>
     * "is_lost":0,<br>
     * "nickname": "42#AI",<br>
     * "gender": "男",<br>
     * "gender_type": 1,<br>
     * "province": "山东",<br>
     * "city": "济宁",<br>
     * "year": "1998",<br>
     * "constellation": "",<br>
     * "figureurl": "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/30",<br>
     * "figureurl_1": "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/50",<br>
     * "figureurl_2": "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/100",<br>
     * "figureurl_qq_1": "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=40&t=1580952111",<br>
     * "figureurl_qq_2": "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=100&t=1580952111",<br>
     * "figureurl_qq": "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=640&t=1580952111",<br>
     * "figureurl_type": "1",<br>
     * "is_yellow_vip": "0",<br>
     * "vip": "0",<br>
     * "yellow_vip_level": "0",<br>
     * "level": "0",<br>
     * "is_yellow_year_vip": "0"<br>
     * }<br>
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo(String accessToken, String openid) {
        String uri = "https://graph.qq.com/user/get_user_info" +//
                "?oauth_consumer_key={appId}" +//
                "&access_token={accessToken}" +//
                "&openid={openid}" +//
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("appId", Constant.QQ_APP_ID);
        params.put("accessToken", accessToken);
        params.put("openid", openid);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class, params);
        Map<String, Object> map = JSON.parseObject(result);
        String nickname = (String) map.get("nickname");
        System.out.println(result);
        System.out.println(nickname);
        return result;
    }

}
