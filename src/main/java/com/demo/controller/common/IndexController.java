package com.demo.controller.common;

import com.demo.constant.UserLoginTypeEnum;
import com.demo.entity.po.UserLoginLog;
import com.demo.entity.pojo.Result;
import com.demo.mapper.UserLoginLogMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.demo.base.ServiceBase.recordLog;

/**
 * <h1>首页api</h1>
 *
 * <p>
 * createDate 2021/01/14 14:32:52
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@AllArgsConstructor
public class IndexController {

    private final HttpServletRequest request;
    private final UserLoginLogMapper userLoginLogMapper;

    /**
     * 首页
     */
    @GetMapping(value = {"", "/"})
    public Result index() {
        UserLoginLog userLoginLog = new UserLoginLog(request, null, UserLoginTypeEnum.ACCOUNT, false);
        recordLog(() -> userLoginLogMapper.insert(//
                new UserLoginLog(request, null, UserLoginTypeEnum.ACCOUNT, false)));
        return Result.o(userLoginLog);
    }

}
