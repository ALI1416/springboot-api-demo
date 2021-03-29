package com.demo.service;

import com.demo.dao.UserMongoDao;
import com.demo.entity.mongo.UserMongo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>用户Mongo服务</h1>
 *
 * <p>
 * createDate 2021/03/27 19:39:47
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Service
@AllArgsConstructor
public class UserMongoService {

    private final UserMongoDao userMongoDao;
    private final MongoTemplate mongoTemplate;

    /**
     * 插入
     */
    public void insert(UserMongo userMongo) {
        userMongoDao.save(userMongo);
    }

    /**
     * 更新
     */
    public void update(UserMongo userMongo) {
        userMongoDao.save(userMongo);
    }

    /**
     * 删除，通过id
     */
    public void deleteById(String id) {
        userMongoDao.deleteById(id);
    }

    /**
     * 查找全部
     */
    public List<UserMongo> findAll() {
        return userMongoDao.findAll();
    }

    /**
     * 查询，通过id
     */
    public UserMongo findById(String id) {
        return userMongoDao.findById(id).orElse(null);
    }

    /**
     * 查询并分页，通过name
     */
    public Page<UserMongo> findByName(UserMongo userMongo) {
        return userMongoDao.findByName(userMongo.getName(), PageRequest.of(userMongo.getPages() - 1,
                userMongo.getRows()));
    }

    /**
     * 关注+1
     */
    public void addFollowers(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("followers");
        mongoTemplate.updateFirst(query, update, UserMongo.class);
    }

}
