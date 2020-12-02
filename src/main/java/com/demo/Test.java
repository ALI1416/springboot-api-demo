package com.demo;

import com.demo.entity.User;
import com.demo.result.BatchResult;
import com.demo.result.Result;
import com.demo.result.ResultCode;
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

        UserVo uv = new UserVo();
        uv.setId(1);
        uv.setAccount("admin");
        uv.setName("ck");
        uv.setGender(1);
        uv.setYear(1998);
        uv.setYearNot(0);
        uv.setYearEnd(2000);
        System.out.println(uv);

        Result r1 = Result.o(ResultCode.OK);
        System.out.println(r1.isOk());
        Result r2 = Result.e(ResultCode.ERROR);
        System.out.println(r2.isOk());

        BatchResult<User> batchResult = new BatchResult<>();
        batchResult.add(u);
        batchResult.add(u);
        batchResult.add(false, u);
        batchResult.add(false, u);
        System.out.println(batchResult);
        System.out.println(batchResult.isOk());
    }

}
