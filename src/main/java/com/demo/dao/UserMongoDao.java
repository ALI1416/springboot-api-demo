package com.demo.dao;

import com.demo.entity.mongo.UserMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <h1>用户Mongo Dao</h1>
 *
 * <p>
 * createDate 2021/03/27 19:34:51
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public interface UserMongoDao extends MongoRepository<UserMongo, String> {

    /**
     * 分页查询，通过name<br>
     * 使用了类似hibernate的查询方法
     */
    Page<UserMongo> findByName(String name, Pageable pageable);

}
