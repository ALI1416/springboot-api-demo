package com.demo.util;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>ElasticSearch工具</h1>
 *
 * <p>
 * 捕获异常请用Exception，因为会出现多种异常情况，比如IOException、RuntimeException等。
 * </p>
 *
 * <p>
 * createDate 2020/12/29 16:23:19
 * </p>
 * 
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Component
@Slf4j
public class EsUtils {

    private static RestHighLevelClient client;

    @Autowired
    public EsUtils(RestHighLevelClient client) {
        EsUtils.client = client;
    }

    /**
     * 创建索引
     * 
     * @param index 索引名
     * @return 是否成功
     */
    public static boolean createIndex(String index) {
        CreateIndexRequest request = new CreateIndexRequest(index);
        try {
            return client.indices().create(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (Exception e) {
            log.error("创建索引异常", e);
            return false;
        }
    }

    /**
     * 存在索引
     * 
     * @param index 索引名
     * @return 是否存在
     */
    public static boolean existIndex(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("查找索引异常", e);
            return false;
        }
    }

    /**
     * 删除索引
     * 
     * @param index 索引名
     * @return 是否成功
     */
    public static boolean deleteIndex(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            return client.indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (Exception e) {
            log.error("删除索引异常", e);
            return false;
        }
    }

    /**
     * 新增文档(随机文档id)
     * 
     * @param index  索引名
     * @param object 文档
     */
    public static IndexResponse addDocument(String index, Object object) {
        return addDocument(index, null, object);
    }

    /**
     * 新增文档
     * 
     * @param index  索引名
     * @param id     文档id
     * @param object 文档
     */
    public static IndexResponse addDocument(String index, String id, Object object) {
        IndexRequest request = new IndexRequest(index);
        request.id(id);
        request.source(JSON.toJSONString(object), XContentType.JSON);
        try {
            return client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("新增文档异常", e);
            return null;
        }
    }

    /**
     * 存在文档
     * 
     * @param index 索引名
     * @param id    文档id
     * @return 是否存在
     */
    public static boolean existDocument(String index, String id) {
        GetRequest request = new GetRequest(index, id);
        // 去除_source
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        try {
            return client.exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("查找文档异常", e);
            return false;
        }
    }

    /**
     * 查找文档
     * 
     * @param index 索引名
     * @param id    文档id
     */
    public static GetResponse getDocument(String index, String id) {
        GetRequest request = new GetRequest(index, id);
        try {
            return client.get(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("查找文档异常", e);
            return null;
        }
    }
}
