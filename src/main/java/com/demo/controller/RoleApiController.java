package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.annotation.Auth;
import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;
import com.demo.entity.vo.RoleApiVo;
import com.demo.service.RoleApiService;
import com.demo.tool.Id;
import com.demo.util.AuthUtils;

import lombok.AllArgsConstructor;

/**
 * <h1>api角色api</h1>
 *
 * <p>
 * createDate 2021/01/23 19:10:15
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("roleApi")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleApiController extends BaseController {

    private final HttpServletRequest request;
    private final RoleApiService roleApiService;

    /**
     * 存在name
     */
    @PostMapping("/existName")
    public Result existName(String name) {
        return Result.o(roleApiService.existName(name));
    }

    /**
     * 新增用户(需name)
     */
    @Auth
    @PostMapping("/insert")
    public Result insert(@RequestBody RoleApiVo roleApi) {
        if (isEmpty(roleApi.getName())) {
            return Result.e1();
        }
        // 用户已存在
        if (roleApiService.existName(roleApi.getName())) {
            return Result.e(ResultCodeEnum.USER_HAS_EXISTED);
        }
        roleApi.setId(Id.next());
        roleApi.setCreateId(AuthUtils.getUserId(request));
        return roleApiService.insert(request, roleApi);
    }

    /**
     * 修改个人信息(只能修改name,comment)
     */
    @Auth
    @PostMapping("/change")
    public Result change(@RequestBody RoleApiVo roleApi) {
        Long id = AuthUtils.getUserId(request);
        RoleApiVo u = new RoleApiVo();
        u.setId(id);
        u.setUpdateId(id);
        u.setName(roleApi.getName());
        return roleApiService.change(u);
    }

    /**
     * 查看个人信息
     */
    @Auth
    @PostMapping("/showInfo")
    public Result showInfo() {
        Long id = AuthUtils.getUserId(request);
        RoleApiVo roleApi = roleApiService.findById(id);
        return Result.o(roleApi);
    }

}
