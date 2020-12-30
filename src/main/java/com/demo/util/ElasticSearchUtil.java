package com.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Title: ElasticsearchUtil
 * @Description: 工具类
 */
//@Component
@Slf4j
public class ElasticSearchUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchUtil.class);

    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_PUT = "PUT";
    public static final String HTTP_METHOD_DELETE = "DELETE";
    public static final String HTTP_METHOD_HEAD = "HEAD";

    @Resource(name = "restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    private static RestHighLevelClient client;

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @PostContruct是spring框架的注解 spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        client = this.restHighLevelClient;
    }

    public static boolean batchInsert(List<?> datas, String indexName) {
        if (datas == null || datas.isEmpty()) {
            return false;
        }
        BulkRequest bulkRequest = new BulkRequest();
        for (Object obj : datas) {
            bulkRequest.add(buildIndexRequest(obj, indexName));
        }
        return batchInsert(bulkRequest, indexName);

    }

    private static boolean batchInsert(BulkRequest bulkRequest, String indexName) {
        long startTime = System.currentTimeMillis();
        try {
            BulkResponse responses = ElasticSearchUtil.bulkPost(bulkRequest);
            // TODO 需要处理响应
            log.info("batchInsert indexName {} to elasticseach datas size {} take {} ms ", indexName,
                    bulkRequest.requests().size(), (System.currentTimeMillis() - startTime));
            return true;

        } catch (Exception e) {
            log.warn("batchInsert {}  to elasticsearch fail ", indexName, e);
            return false;
        }
    }

    public static IndexRequest buildIndexRequest(Object obj, String indexName, String id) {
        IndexRequest indexRequest = new IndexRequest(indexName);
        if (id != null && !id.isEmpty()) {
            indexRequest.id(id);
        }
        String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.UseSingleQuotes);
        indexRequest.source(JSON.parseObject(json, Map.class));
        return indexRequest;

    }

    private static IndexRequest buildIndexRequest(Object obj, String indexName) {
        return buildIndexRequest(obj, indexName, null);

    }

    /**
     * 插入数据
     * 
     * @param index
     * @param object
     * @return
     */
    public static String addData(String index, JSONObject object) {
        IndexRequest indexRequest = new IndexRequest(index);
        try {
            indexRequest.source(mapper.writeValueAsString(object), XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.getId();
        } catch (Exception e) {
            log.warn("addData fail  ", e);
        }
        return null;
    }

    /**
     *
     * @param index
     * @param id
     * @param object
     * @return
     */
    public static String modifyData(String index, String id, JSONObject object) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(index, id);
            updateRequest.doc(mapper.writeValueAsString(object), XContentType.JSON);
            UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
//            urb.setDoc(data);
//            urb.setDetectNoop(false);//默认是true
//            urb.execute().actionGet();
            return response.toString();
        } catch (Exception e) {
            log.warn("modifyData fail  ", e);
        }
        return null;
    }

    /**
     *
     * @Author zhurs
     * @Date 18:02 2019/11/29
     * @param index
     * @param objects
     * @return boolean
     * @Description
     */
    public static boolean bulkPost(String index, List<?> objects) {
        BulkRequest bulkRequest = new BulkRequest();
        BulkResponse response = null;
        // 最大数量不得超过20万
        for (Object object : objects) {
            String json = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.UseSingleQuotes);
            IndexRequest request = new IndexRequest(index);
            request.source(JSON.parseObject(json, Map.class));
            bulkRequest.add(request);
        }
        try {
            response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.hasFailures();
    }

    /**
     *
     * @Author zhurs
     * @Date 18:01 2019/11/29
     * @param index
     * @param objects
     * @return void
     * @Description
     */
    public static void bulkAsyncPost(String index, List<?> objects) {
        BulkRequest bulkRequest = new BulkRequest();
        // 最大数量不得超过20万
        for (Object object : objects) {
            String json = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.UseSingleQuotes);
            IndexRequest request = new IndexRequest(index);
            request.source(JSON.parseObject(json, Map.class));
            bulkRequest.add(request);
        }
        client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                LOGGER.info(bulkItemResponses.buildFailureMessage());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     *
     * @Author zhurs
     * @Date 18:10 2019/11/29
     * @param bulkRequest
     * @return boolean
     * @Description
     */
    public static BulkResponse bulkPost(BulkRequest bulkRequest) throws IOException {
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return response;
    }

    /**
     *
     * @Author zhurs
     * @Date 18:10 2019/11/29
     * @param bulkRequest
     * @return void
     * @Description
     */
    public static void bulkAsyncPost(BulkRequest bulkRequest) {

        client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                LOGGER.info(bulkItemResponses.buildFailureMessage());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Boolean getSourceByFilters(String index, String fieldName, String filtervalue) throws IOException {

        // Single request quantity
        int elasticsearchsize = 10;

        // High Level Client init
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // Aggregate statement
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                // .must(QueryBuilders.queryStringQuery(filters))
                // .must(QueryBuilders.termsQuery("uri",filters))
                .must(QueryBuilders.matchPhraseQuery(fieldName, filtervalue))
//                    .must(QueryBuilders.rangeQuery("@timestamp").gte(gte).lte(lte))
        ).timeout(new TimeValue(10000, TimeUnit.SECONDS)).size(elasticsearchsize);

        searchRequest.source(searchSourceBuilder);

        // Print the executed DSL statement, which can be used directly in kibana
        // LOGGER.info(searchSourceBuilder.toString());
//        BasicHeader header = new BasicHeader("Content-Type", "application/json");

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        if (searchResponse.getHits().getTotalHits().value == 0) {
            return null;
        } else {
            Boolean kafkaresstatus = new Boolean(true);
            if ("OK".equals(searchResponse.status().toString())) {
                List list = new ArrayList<>();

                String scrollId = searchResponse.getScrollId();
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchResponse.getHits().getHits()) {
                    String res = hit.getSourceAsString();
                }

                long totalHits = searchResponse.getHits().getTotalHits().value;
                long length = searchResponse.getHits().getHits().length;
                LOGGER.info("A total of [{}] data was retrieved, and the number of data processed [{}]", totalHits,
                        length);

                while (searchHits != null && searchHits.length > 0) {

                    SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                    scrollRequest.scroll(scroll);
                    searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);

                    for (SearchHit hit : searchResponse.getHits().getHits()) {
                        String res = hit.getSourceAsString();
                    }
                    length += searchResponse.getHits().getHits().length;
                    LOGGER.info("A total of [{}] data was retrieved, and the number of data processed [{}]", totalHits,
                            length);
                    if (length == totalHits) {
                        break;
                    }
                }
                ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
                clearScrollRequest.addScrollId(scrollId);
                ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest,
                        RequestOptions.DEFAULT);
                boolean succeeded = clearScrollResponse.isSucceeded();
                return kafkaresstatus;
            } else {
                return false;
            }
        }

    }

    /**
     * 获取低水平客户端
     * 
     * @return
     */
    public static RestClient getLowLevelClient() {
        return client.getLowLevelClient();
    }

    public static Response search(String method, String endpoint, String queryString) throws IOException {
        Map<String, String> params = Collections.emptyMap();
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);

        Request request = new Request(method, endpoint);
        request.setEntity(entity);
        Response response = client.getLowLevelClient().performRequest(request);
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        JSONObject jsonObject = JSON.parseObject(responseBody);
        return response;

    }

    /**
     * 根据经纬度查询范围查找
     * 
     * @param index
     * @param longitude
     * @param latitude
     * @param distance
     * @return
     * @throws IOException
     */
    public static SearchResponse geoDistanceQuery(String index, Float longitude, Float latitude, String distance)
            throws IOException {

        if (longitude == null || latitude == null) {
            return null;
        }
        // 拼接条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        QueryBuilder isdeleteBuilder = QueryBuilders.termQuery("isdelete", false);
        // 以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(latitude, longitude);
        // 查询单位：km
        distanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);
//        boolQueryBuilder.must(isdeleteBuilder);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return searchResponse;

    }

    /**
     * *解析结果字符串
     * 
     * @param resultStr
     * @return
     */
    public static String resolveBusiDetailResultStr(String resultStr) {
        if (resultStr == null || resultStr.isEmpty()) {
            return null;
        }
//        System.out.println("resultStr:" + resultStr);
        JSONObject jsonObject = JSON.parseObject(resultStr);
        if (jsonObject == null) {
            return null;
        }
//        System.out.println("jsonObject:" + jsonObject.toString());
        JSONObject hits1 = jsonObject.getJSONObject("hits");
        if (hits1 == null) {
            return null;
        }
//        System.out.println("hits-1:" + hits1.toString());
        JSONArray hits2 = hits1.getJSONArray("hits");
        if (hits2 == null || hits2.size() <= 0 || hits2.get(0) == null) {
            return null;
        }
//        System.out.println("hits-2:" + hits2.toString());
        Object hits2Array0 = hits2.get(0);
        if (hits2Array0 == null) {
            return null;
        }
//        System.out.println("hits2Array0:" + hits2Array0.toString());
        JSONObject hits2Array0StrObj = JSON.parseObject(hits2Array0.toString());
        if (hits2Array0StrObj == null) {
            return null;
        }
        JSONObject _source = hits2Array0StrObj.getJSONObject("_source");
        if (_source == null) {
            return null;
        }
        String _sourceStr = _source.toString();
//        System.out.println("_sourceStr:" + _sourceStr);
        return _sourceStr;
    }

}
