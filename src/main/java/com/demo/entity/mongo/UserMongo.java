package com.demo.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <h1>用户Mongo实体</h1>
 *
 * <p>
 * createDate 2021/03/27 19:12:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@Setter
// 声明为MongoDB文档，collection是别名
@Document(collection = "user")
// 复合索引，不推荐写在代码中
// @CompoundIndex(def = "{'followers': 1 , 'following': -1 }")
public class UserMongo {

    /**
     * id
     */
    // 指明id，当字段名为id时，不需要指明
    @Id
    private String id;
    /**
     * 用户名
     */
    // 索引
    @Indexed
    private String name;
    /**
     * 关注人数
     */
    // 字段别名
    @Field("followers")
    private Integer followers;
    /**
     * 粉丝人数
     */
    private Integer following;
    /**
     * 分页-页码
     */
    private Integer pages;
    /**
     * 分页-每页条数
     */
    private Integer rows;

}
