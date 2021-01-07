package com.demo.controller;

import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.util.EsUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("es")
public class EsController {

    /**
     * 创建索引
     */
    @PostMapping("/createIndex")
    public Result createIndex(String index) {
        boolean ok = EsUtils.createIndex(index);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 是否存在索引
     */
    @PostMapping("/existIndex")
    public Result existIndex(String index) {
        boolean ok = EsUtils.existIndex(index);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 删除索引
     */
    @PostMapping("/deleteIndex")
    public Result deleteIndex(String index) {
        boolean ok = EsUtils.deleteIndex(index);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 新增文档
     */
    @PostMapping("/addDocument")
    public Result addDocument(String index, String id, @RequestBody User user) {
        IndexResponse ok = EsUtils.addDocument(index, id, user);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 是否存在文档
     */
    @PostMapping("/existDocument")
    public Result existDocument(String index, String id) {
        boolean ok = EsUtils.existDocument(index, id);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 查找文档
     */
    @PostMapping("/getDocument")
    public Result getDocument(String index, String id) {
        GetResponse ok = EsUtils.getDocument(index, id);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 修改文档
     */
    @PostMapping("/updateDocument")
    public Result updateDocument(String index, String id, @RequestBody User user) {
        UpdateResponse ok = EsUtils.updateDocument(index, id, user);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 删除文档
     */
    @PostMapping("/deleteDocument")
    public Result deleteDocument(String index, String id) {
        DeleteResponse ok = EsUtils.deleteDocument(index, id);
        System.out.println(ok);
        return Result.o(ok);
    }

}
