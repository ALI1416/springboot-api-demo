package com.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.pojo.Result;
import com.demo.util.ElasticSearchUtils;

/**
 * <h1>ElasticSearch api</h1>
 *
 * <p>
 * createDate 2020/12/29 16:31:27
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
@RequestMapping("elasticSearch")
public class ElasticSearchController {

    /**
     * 创建索引
     */
    @PostMapping("/createIndex")
    public Result createIndex(String index) {
        boolean ok = ElasticSearchUtils.createIndex(index);
        System.out.println(ok);
        return Result.o(ok);
    }

}
