package com.demo.util;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>ElasticSearch工具</h1>
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
public class ElasticSearchUtils {

    private static RestHighLevelClient client;

    @Autowired
    public ElasticSearchUtils(RestHighLevelClient client) {
        ElasticSearchUtils.client = client;
    }

    /**
     * 创建索引
     * 
     * @param index 索引名
     * @return 是否创建成功
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
}
