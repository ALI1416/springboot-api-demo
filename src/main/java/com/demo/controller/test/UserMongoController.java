package com.demo.controller.test;

import com.demo.entity.mongo.UserMongo;
import com.demo.entity.pojo.Result;
import com.demo.service.UserMongoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户Mongo api</h1>
 *
 * <p>
 * createDate 2021/03/27 19:51:39
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("mongo")
@AllArgsConstructor
public class UserMongoController {

    private final UserMongoService userMongoService;

    /**
     * 插入
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody UserMongo mongo) {
        userMongoService.insert(mongo);
        return Result.o();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserMongo mongo) {
        userMongoService.update(mongo);
        return Result.o();
    }

    /**
     * 删除，通过id
     */
    @PostMapping("/deleteById")
    public Result deleteById(@RequestBody UserMongo mongo) {
        userMongoService.deleteById(mongo.getId());
        return Result.o();
    }

    /**
     * 查询全部
     */
    @PostMapping("/findAll")
    public Result findAll() {
        return Result.o(userMongoService.findAll());
    }

    /**
     * 查询，通过id
     */
    @PostMapping("/findById")
    public Result findById(@RequestBody UserMongo mongo) {
        return Result.o(userMongoService.findById(mongo.getId()));
    }

    /**
     * 查询并分页，通过name
     */
    @PostMapping("/findByName")
    public Result findByName(@RequestBody UserMongo mongo) {
        return Result.o(userMongoService.findByName(mongo));
    }

    /**
     * 关注+1
     */
    @PostMapping("/addFollowers")
    public Result addFollowers(@RequestBody UserMongo mongo) {
        userMongoService.addFollowers(mongo.getId());
        return Result.o();
    }


}
