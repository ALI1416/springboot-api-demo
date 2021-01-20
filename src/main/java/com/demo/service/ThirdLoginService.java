package com.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.demo.constant.Constant;
import com.demo.constant.RedisConstant;
import com.demo.constant.ResultCodeEnum;
import com.demo.constant.UserLoginTypeConstant;
import com.demo.dao.UserLoginLogDao;
import com.demo.entity.po.User;
import com.demo.entity.po.UserLoginLog;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.UserVo;
import com.demo.tool.Id;
import com.demo.util.RedisUtils;
import com.demo.util.StringUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>第三方登录服务</h1>
 *
 * <p>
 * createDate 2021/01/14 14:29:54
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ThirdLoginService extends BaseService {

    private final UserLoginLogDao userLoginLogDao;
    private final UserService userService;

    /**
     * qq回调
     * 
     * @param request HttpServletRequest
     * @param sign    sign
     * @param code    code
     * @param qqState qqState
     */
    public Result qqCallback(HttpServletRequest request, String sign, String code, String qqState) {
        String name = sign + RedisConstant.QQ_STATE_SUFFIX;
        // 从redis中获取qqState
        String redisQqState = (String) RedisUtils.get(name);
        // 获取后要删除
        RedisUtils.delete(name);
        // qqState错误
        if (!qqState.equalsIgnoreCase(redisQqState)) {
            return Result.e(ResultCodeEnum.THIRD_PARTY_LOGIN_FAILED);
        }
        // 获取token
        String tokenObj = this.getToken(code);
        Map<String, Object> tokenMap = JSON.parseObject(tokenObj);
        // 得到access_token
        String accessToken = (String) tokenMap.get("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            return Result.e(ResultCodeEnum.THIRD_PARTY_LOGIN_FAILED);
        }
        // 获取openid和unionid
        String openidAndUnionidObj = this.getOpenidAndUnionid(accessToken);
        Map<String, Object> openidAndUnionidMap = JSON.parseObject(openidAndUnionidObj);
        // 得到openid
        String openid = (String) openidAndUnionidMap.get("openid");
        User u = userService.findByQqOpenid(openid);
        // openid已存在，去登录账号
        if (u != null) {
            // 日志(登录成功)
            recordLog(
                    () -> userLoginLogDao.insert(new UserLoginLog(request, u.getId(), UserLoginTypeConstant.QQ, true)));
            // redis放入userId
            RedisUtils.hashSet(sign, RedisConstant._USER_ID, u.getId());
            return Result.o();
        }
        // openid不存在，去注册账号
        // 先获取用户的qq信息
        String userInfoObj = this.getUserInfo(accessToken, openid);
        Map<String, Object> userInfoMap = JSON.parseObject(userInfoObj);
        String userName = (String) userInfoMap.get("nickname");
        Integer userGender = "男".equals(userInfoMap.get("gender")) ? 1 : 0;
        Integer userYear = Integer.valueOf((String) userInfoMap.get("year"));
        UserVo user = new UserVo();
        user.setId(Id.next());
        user.setName(userName);
        user.setGender(userGender);
        user.setYear(userYear);
        user.setQqName(userName);
        user.setQqOpenid(openid);
        // 去注册账号
        return userService.registerByQq(request, sign, user);
    }

    /**
     * 获取token<br>
     * 返回结果<br>
     * {<br>
     * "access_token":"D7BF7285C42531F41831A1819EE1A38F",<br>
     * "expires_in":"7776000",<br>
     * "refresh_token":"7D470F114079F49E222B31590408707F"<br>
     * }<br>
     *
     * @param code code
     */
    public String getToken(String code) {
        String uri = "https://graph.qq.com/oauth2.0/token" + //
                "?grant_type=authorization_code" + //
                "&client_id={appId}" + //
                "&client_secret={appKey}" + //
                "&code={code}" + //
                "&redirect_uri={callbackUrl}" + //
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("appId", Constant.QQ_APP_ID);
        params.put("appKey", Constant.QQ_APP_KEY);
        params.put("code", code);
        params.put("callbackUrl", Constant.QQ_CALLBACK_URL);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class, params);
    }

    /**
     * 获取openid和unionid<br>
     * 返回结果<br>
     * {<br>
     * "client_id":"101925994",<br>
     * "openid":"91449D4BB19893F674C07B111C1BB4FB",<br>
     * "unionid":"UID_3C6246E641847F77CF90BA17D543F497"<br>
     * }<br>
     *
     * @param accessToken accessToken
     */
    public String getOpenidAndUnionid(String accessToken) {
        String uri = "https://graph.qq.com/oauth2.0/me" + //
                "?access_token={accessToken}" + //
                "&unionid=1" + //
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class, params);
    }

    /**
     * 获取用户信息<br>
     * 返回结果<br>
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
     * "figureurl":
     * "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/30",<br>
     * "figureurl_1":
     * "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/50",<br>
     * "figureurl_2":
     * "http://qzapp.qlogo.cn/qzapp/101925994/91449D4BB19893F674C07B111C1BB4FB/100",<br>
     * "figureurl_qq_1":
     * "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=40&t=1580952111",<br>
     * "figureurl_qq_2":
     * "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=100&t=1580952111",<br>
     * "figureurl_qq":
     * "http://thirdqq.qlogo.cn/g?b=oidb&k=4LjicYXdGYvgmfqsbrzhcfA&s=640&t=1580952111",<br>
     * "figureurl_type": "1",<br>
     * "is_yellow_vip": "0",<br>
     * "vip": "0",<br>
     * "yellow_vip_level": "0",<br>
     * "level": "0",<br>
     * "is_yellow_year_vip": "0"<br>
     * }<br>
     *
     * @param accessToken accessToken
     * @param openid      openid
     */
    public String getUserInfo(String accessToken, String openid) {
        String uri = "https://graph.qq.com/user/get_user_info" + //
                "?oauth_consumer_key={appId}" + //
                "&access_token={accessToken}" + //
                "&openid={openid}" + //
                "&fmt=json";
        Map<String, String> params = new HashMap<>();
        params.put("appId", Constant.QQ_APP_ID);
        params.put("accessToken", accessToken);
        params.put("openid", openid);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class, params);
    }

}
