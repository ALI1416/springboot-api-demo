package com.demo;

import java.util.HashMap;
import java.util.Map;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.Result;
import com.demo.entity.ResultBatch;
import com.demo.po.User;
import com.demo.vo.UserVo;

/**
 * <h1>测试类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Test {

    public static void main(String[] args) {
        User u = new User();
        u.setId(0);
        u.setAccount("root");
        u.setName("root");
        u.setGender(0);
        u.setYear(0);
        System.out.println(u);
        Map<String, Object> map = new HashMap<>();
        map.put("user", 1234);
        u.setMap(map);
        System.out.println(u);

        UserVo uv = new UserVo();
        uv.setId(1);
        uv.setAccount("admin");
        uv.setName("ck");
        uv.setGender(1);
        uv.setYear(1998);
        uv.setYearNot(0);
        uv.setYearEnd(2000);
        System.out.println(uv);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("user", u);
        uv.setMap(map2);
        System.out.println(uv);

        Result r1 = Result.o(ResultCodeEnum.OK);
        System.out.println(r1.isOk());
        Result r2 = Result.e(ResultCodeEnum.ERROR);
        System.out.println(r2.isOk());
        Result r3 = Result.e(ResultCodeEnum.USER_ACCOUNT_FORBIDDEN, "哈哈哈哈", u);
        System.out.println(r3);
        User ru = (User) r3.getData();
        System.out.println(ru);

        ResultBatch<User> batchResult = new ResultBatch<>();
        batchResult.add(u);
        batchResult.add(u);
        batchResult.add(false, u);
        batchResult.add(false, u);
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());

    }

}
