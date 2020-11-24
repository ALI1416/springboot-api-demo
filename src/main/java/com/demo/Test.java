package com.demo;

import com.demo.entity.User;
import com.demo.vo.UserVo;

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
    }

}
