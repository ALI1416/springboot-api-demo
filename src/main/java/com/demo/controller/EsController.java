package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.util.EsUtils;

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
     * 新增文档(随机文档id)
     */
    @PostMapping("/addDocument")
    public Result addDocument(String index, @RequestBody User user) {
        IndexResponse ok = EsUtils.addDocument(index, user);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 新增文档
     */
    @PostMapping("/addDocument2")
    public Result addDocument2(String index, String id, @RequestBody User user) {
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

    /**
     * 批量新增文档(随机文档id)
     */
    @PostMapping("/addDocumentBulk")
    public Result addDocumentBulk(String index, @RequestBody List<User> objects) {
        boolean ok = EsUtils.addDocumentBulk(index, objects);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 批量新增文档
     */
    @PostMapping("/addDocumentBulk2")
    public Result addDocumentBulk2(String index, @RequestBody Map<String, User> objects) {
        boolean ok = EsUtils.addDocumentBulk(index, objects);
        System.out.println(ok);
        return Result.o(ok);
    }

    /**
     * 查询文档
     */
    @PostMapping("/search")
    public Result search(String index, String value) {
        List<Map<String, Object>> ok = EsUtils.search(index, null, value);
        System.out.println(ok);
        return Result.o(ok);
    }

}
