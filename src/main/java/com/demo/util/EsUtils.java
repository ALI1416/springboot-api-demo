package com.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
    public EsUtils(RestHighLevelClient restHighLevelClient) {
        EsUtils.client = restHighLevelClient;
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
     * @param <T>
     *
     * @param index  索引名
     * @param object 文档名
     */
    public static <T> IndexResponse addDocument(String index, T object) {
        return addDocument(index, null, object);
    }

    /**
     * 新增文档
     * 
     * @param <T>
     *
     * @param index  索引名
     * @param id     文档id
     * @param object 对象
     */
    public static <T> IndexResponse addDocument(String index, String id, T object) {
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

    /**
     * 更新文档
     * 
     * @param <T>
     *
     * @param index  索引名
     * @param id     文档id
     * @param object 对象
     */
    public static <T> UpdateResponse updateDocument(String index, String id, T object) {
        UpdateRequest request = new UpdateRequest(index, id);
        request.doc(JSON.toJSONString(object), XContentType.JSON);
        try {
            return client.update(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("更新文档异常", e);
            return null;
        }
    }

    /**
     * 删除文档
     *
     * @param index 索引名
     * @param id    文档id
     */
    public static DeleteResponse deleteDocument(String index, String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        try {
            return client.delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("删除文档异常", e);
            return null;
        }
    }

    /**
     * 批量新增文档
     * 
     * @param <T>
     *
     * @param index   索引名
     * @param objects 带文档id对象Map
     */
    public static <T> boolean addDocumentBulk(String index, Map<String, T> objects) {
        BulkRequest request = new BulkRequest();
        for (Entry<String, T> object : objects.entrySet()) {
            request.add(new IndexRequest(index)//
                    .id(object.getKey())//
                    .source(JSON.toJSONString(object.getValue()), XContentType.JSON));
        }
        try {
            return !client.bulk(request, RequestOptions.DEFAULT).hasFailures();
        } catch (Exception e) {
            log.error("批量新增文档异常", e);
            return false;
        }
    }

    /**
     * 批量新增文档(随机文档id)
     * 
     * @param <T>
     *
     * @param index   索引名
     * @param objects 随机文档id对象List
     */
    public static <T> boolean addDocumentBulk(String index, List<T> objects) {
        BulkRequest request = new BulkRequest();
        for (T object : objects) {
            request.add(new IndexRequest(index)//
                    .source(JSON.toJSONString(object), XContentType.JSON));
        }
        try {
            return !client.bulk(request, RequestOptions.DEFAULT).hasFailures();
        } catch (Exception e) {
            log.error("批量新增文档异常", e);
            return false;
        }
    }

    /**
     * 查询文档
     *
     * @param index   索引名
     * @param builder 查询构造器
     */
    public static List<Map<String, Object>> search(String index, SearchSourceBuilder builder, String value) {
        SearchRequest request = new SearchRequest(index);

//        AnalyzeRequest an = new AnalyzeRequest(value).analyzer("ik_smart");
        builder = new SearchSourceBuilder();
        String field = "account";
        /* 精确查询(匹配字段,匹配值) */
//        builder.query(QueryBuilders.termQuery(field, value));
        /* 模糊查询(匹配字段,匹配值) */
//        最大切分  ik_samrt
//        详细切分  ik_max_word
        builder.query(QueryBuilders.matchQuery(field, value).analyzer("ik_max_word"));
        /* 高亮查询 */
        builder.highlighter(new HighlightBuilder()//
                .field(field)// 匹配字段
                .requireFieldMatch(false)// 匹配所有字段
                .preTags("<span style='color:red'>")// 内容前缀
                .postTags("</span>"));// 内容后缀
        /* 分页查询(从第几条开始查询,查询多少条) */
        builder.from(0).size(10);

        request.source(builder);
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for (SearchHit documentFields : response.getHits().getHits()) {
                // list.add(documentFields.getSourceAsMap());
                /* 高亮 */
                // 原来的内容
                Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
                // 找到所有的高亮字段
                Map<String, HighlightField> highlightFieldMap = documentFields.getHighlightFields();
                // 遍历所有的高亮字段
                for (Map.Entry<String, HighlightField> highlightFieldEntry : highlightFieldMap.entrySet()) {
                    // 高亮字段的内容
                    HighlightField highlightField = highlightFieldEntry.getValue();
                    if (highlightField != null) {
                        // 高亮字段所有行内容
                        Text[] fragments = highlightField.getFragments();
                        StringBuilder texts = new StringBuilder();
                        for (Text text : fragments) {
                            texts.append(text);
                        }
                        // 替换原来的内容(高亮字段,所有行内容)
                        sourceAsMap.put(highlightFieldEntry.getKey(), texts);
                    }
                }
                list.add(sourceAsMap);
            }
            return list;
        } catch (Exception e) {
            log.error("查询文档异常", e);
            return null;
        }
    }
}
