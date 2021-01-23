package com.demo.controller;

import com.demo.entity.po.User;
import com.demo.entity.pojo.Result;
import com.demo.util.EsUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        String mapping = "{\n" + //
                "  \"properties\": {\n" + //
                "    \"account\": {\n" + // 字段名
                "      \"type\": \"text\",\n" + // 类型keyword、text
                "      \"analyzer\": \"ik_max_word\",\n" + // 分词器standard、ik_smart、ik_max_word
                "      \"search_analyzer\": \"ik_max_word\"\n" + // 搜索用分词器
                "    }\n" + //
                "  }\n" + //
                "}";
        boolean ok = EsUtils.createIndex(index, mapping);
        return Result.o(ok);
    }

    /**
     * 是否存在索引
     */
    @PostMapping("/existIndex")
    public Result existIndex(String index) {
        boolean ok = EsUtils.existIndex(index);
        return Result.o(ok);
    }

    /**
     * 删除索引
     */
    @PostMapping("/deleteIndex")
    public Result deleteIndex(String index) {
        boolean ok = EsUtils.deleteIndex(index);
        return Result.o(ok);
    }

    /**
     * 新增文档(随机文档id)
     */
    @PostMapping("/addDocument")
    public Result addDocument(String index, @RequestBody User user) {
        IndexResponse ok = EsUtils.addDocument(index, user);
        return Result.o(ok);
    }

    /**
     * 新增文档
     */
    @PostMapping("/addDocument2")
    public Result addDocument2(String index, String id, @RequestBody User user) {
        IndexResponse ok = EsUtils.addDocument(index, id, user);
        return Result.o(ok);
    }

    /**
     * 是否存在文档
     */
    @PostMapping("/existDocument")
    public Result existDocument(String index, String id) {
        boolean ok = EsUtils.existDocument(index, id);
        return Result.o(ok);
    }

    /**
     * 查找文档
     */
    @PostMapping("/getDocument")
    public Result getDocument(String index, String id) {
        GetResponse ok = EsUtils.getDocument(index, id);
        return Result.o(ok);
    }

    /**
     * 修改文档
     */
    @PostMapping("/updateDocument")
    public Result updateDocument(String index, String id, @RequestBody User user) {
        UpdateResponse ok = EsUtils.updateDocument(index, id, user);
        return Result.o(ok);
    }

    /**
     * 删除文档
     */
    @PostMapping("/deleteDocument")
    public Result deleteDocument(String index, String id) {
        DeleteResponse ok = EsUtils.deleteDocument(index, id);
        return Result.o(ok);
    }

    /**
     * 批量新增文档(随机文档id)
     */
    @PostMapping("/addDocumentBulk")
    public Result addDocumentBulk(String index, @RequestBody List<User> objects) {
        boolean ok = EsUtils.addDocumentBulk(index, objects);
        return Result.o(ok);
    }

    /**
     * 批量新增文档
     */
    @PostMapping("/addDocumentBulk2")
    public Result addDocumentBulk2(String index, @RequestBody Map<String, User> objects) {
        boolean ok = EsUtils.addDocumentBulk(index, objects);
        return Result.o(ok);
    }

    /**
     * 查询文档
     */
    @PostMapping("/search")
    public Result search(String index, String value) {
        String field = "account";
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(field, value);
        HighlightBuilder highlightBuilder = new HighlightBuilder().field(field)// 匹配字段
                // .requireFieldMatch(false)// 匹配所有字段
                // .preTags("<span style='color:red'>")// 内容前缀
                // .postTags("</span>")// 内容后缀
                ;
        SearchResponse searchResponse = EsUtils.search(index, queryBuilder, highlightBuilder, 1, 10, null);
        return Result.o(EsUtils.extractHighlightResult(searchResponse));
    }

    /**
     * 分析文本
     */
    @PostMapping("/analyze")
    public Result analyze(String analyzer, String text) {
        AnalyzeResponse ok = EsUtils.analyze(analyzer, text);
        return Result.o(ok);
    }

}
