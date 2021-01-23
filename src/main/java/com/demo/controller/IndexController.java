package com.demo.controller;

import com.demo.constant.UserLoginTypeEnum;
import com.demo.dao.UserLoginLogDao;
import com.demo.entity.po.UserLoginLog;
import com.demo.entity.pojo.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.demo.service.BaseService.recordLog;

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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final HttpServletRequest request;
    private final UserLoginLogDao userLoginLogDao;

    /**
     * 首页
     */
    @GetMapping(value = {"", "/"})
    public Result index() {
        UserLoginLog userLoginLog = new UserLoginLog(request, null, UserLoginTypeEnum.ACCOUNT, false);
        recordLog(() -> userLoginLogDao.insert(//
                new UserLoginLog(request, null, UserLoginTypeEnum.ACCOUNT, false)));
        return Result.o(userLoginLog);
    }

}
