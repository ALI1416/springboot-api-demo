package com.demo.util;

import com.demo.constant.RedisExpireEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <h1>redis工具</h1>
 *
 * <p>
 * createDate 2020/12/04 15:57:36
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 指定失效时间(秒)
     */
    public static Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取失效时间(秒)
     */
    public static Long expire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     */
    public static Boolean has(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个
     */
    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     */
    public static Long delete(String... key) {
        return redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 模糊查询获取key值
     */
    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 使用Redis的消息队列
     */
    public static void convertAndSend(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    // ============================String=============================

    /**
     * 获取
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 放入
     */
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 放入并设置时间(秒，time<=0无限期)
     */
    public static void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 递增
     */
    public static Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    // ================================Map=================================

    /**
     * 获取map指定项的键值
     *
     * @param key  键
     * @param item 项
     */
    public static Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取map所有键值
     *
     * @param key 键
     */
    public static Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置map所有键值
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public static void hashSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 设置map所有键值并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public static Boolean hashSet(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    /**
     * 向map中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public static void hashSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向map中放入数据并设置时间,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public static Boolean hashSet(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        return expire(key, time);
    }

    /**
     * 删除map中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static Long hashDelete(String key, Object... item) {
        return redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断map中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    public static Boolean hashHas(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * map递增。如果不存在,就会创建一个并把新增后的值返回
     *
     * @param key   键
     * @param item  项
     * @param delta 要增加多少
     */
    public static Double hashIncrement(String key, String item, double delta) {
        return redisTemplate.opsForHash().increment(key, item, delta);
    }

    // ============================Set=============================

    /**
     * 获取
     *
     * @param key 键
     */
    public static Set<Object> setGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询是否存在
     *
     * @param key   键
     * @param value 值
     */
    public static Boolean setHas(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long setSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long setSet(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (count != null && count > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public static Long setSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static Long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ===============================List=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public static List<Object> listGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public static Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public static Object listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public static Long listSet(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static Long listSet(String key, Object value, long time) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        if (count != null && count > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public static Long listSet(String key, List<Object> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static Long listSet(String key, List<Object> value, long time) {
        Long count = redisTemplate.opsForList().rightPushAll(key, value);
        if (count != null && count > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public static void listUpdate(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     */
    public static Long listRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    // =========BoundListOperations用法============

    /**
     * 将数据添加到Redis的list中（从右边添加）
     *
     * @param listKey    key列表
     * @param expireEnum 有效期的枚举类
     * @param values     待添加的数据
     */
    public static void addToListRight(String listKey, RedisExpireEnum expireEnum, Object... values) {
        // 绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        // 插入数据
        boundValueOperations.rightPushAll(values);
        // 设置过期时间
        boundValueOperations.expire(expireEnum.getTime(), expireEnum.getTimeUnit());
    }

    /**
     * 根据起始结束序号遍历Redis中的list
     *
     * @param listKey key列表
     * @param start   起始序号
     * @param end     结束序号
     */
    public static List<Object> rangeList(String listKey, long start, long end) {
        // 绑定操作--查询数据
        return redisTemplate.boundListOps(listKey).range(start, end);
    }

    /**
     * 弹出右边的值 --- 并且移除这个值
     *
     * @param listKey key列表
     */
    public static Object rightPop(String listKey) {
        // 绑定操作
        return redisTemplate.boundListOps(listKey).rightPop();
    }

}
