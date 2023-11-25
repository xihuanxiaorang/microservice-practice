package fun.xiaorang.microservice.common.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/22 23:12
 */
@RequiredArgsConstructor
@Component
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存过期时间（秒），缓存必须存在，否则设置失败
     *
     * @param key     键
     * @param timeout 过期时间（秒）
     * @return boolean true：设置成功，false：设置失败
     */
    public boolean expire(@NonNull final String key, final long timeout) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, TimeUnit.SECONDS));
    }

    /**
     * 设置缓存过期时间，缓存必须存在，否则设置失败
     *
     * @param key     键
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return boolean true：设置成功，false：设置失败
     */
    public boolean expire(@NonNull final String key, final long timeout, @NonNull final TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取缓存过期时间（秒）
     *
     * @param key 键
     * @return Long 过期时间（秒），-1：永久有效，-2：缓存不存在（或已过期）
     */
    public Long getExpireTime(@NonNull final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断缓存是否存在
     *
     * @param key 键
     * @return boolean true：存在，false：不存在
     */
    public boolean hasKey(@NonNull final String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    // ============================ String =============================

    /**
     * 删除缓存
     *
     * @param key 键
     * @return boolean true：删除成功，false：删除失败
     */
    public boolean delete(@NonNull final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除缓存
     *
     * @param keys 键
     */
    public void delete(@NonNull final String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return Object 值
     */
    public Object get(@NonNull final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(@NonNull final String key, @NonNull final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间（秒）
     */
    public void set(@NonNull final String key, @NonNull final Object value, @NonNull final long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 缓存递增
     *
     * @param key   键
     * @param delta 递增因子
     * @return Long 递增后的值
     */
    public Long increment(@NonNull final String key, final long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 缓存递减
     *
     * @param key   键
     * @param delta 递减因子
     * @return Long 递减后的值
     */
    public Long decrement(@NonNull final String key, final long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    // ================================ Hash ===================================

    /**
     * 获取hash缓存
     *
     * @param key     键
     * @param hashKey hash键
     * @return Object 值
     */
    public Object hGet(@NonNull final String key, @NonNull final String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取hash缓存中的所有键值对
     *
     * @param key 键
     * @return Map<Object, Object> 键值对
     */
    public Map<Object, Object> hGet(@NonNull final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置hash缓存
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     */
    public void hSet(@NonNull final String key, @NonNull final String hashKey, @NonNull final Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 设置hash缓存
     *
     * @param key     键
     * @param hashKey hash键
     * @param value   值
     * @param timeout 过期时间（秒）
     */
    public void hSet(@NonNull final String key, @NonNull final String hashKey, @NonNull final Object value, final long timeout) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        expire(key, timeout);
    }

    /**
     * 设置hash缓存
     *
     * @param key 键
     * @param map 值
     */
    public void hSet(@NonNull final String key, @NonNull final Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 设置hash缓存
     *
     * @param key     键
     * @param map     值
     * @param timeout 过期时间（秒）
     */
    public void hset(@NonNull final String key, @NonNull final Map<String, Object> map, final long timeout) {
        redisTemplate.opsForHash().putAll(key, map);
        expire(key, timeout);
    }

    /**
     * 删除hash缓存
     *
     * @param key      键
     * @param hashKeys hash键
     * @return Long 删除的数量
     */
    public Long hDelete(@NonNull final String key, @NonNull final Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断hash缓存是否存在
     *
     * @param key     键
     * @param hashKey hash键
     * @return boolean true：存在，false：不存在
     */
    public boolean hHasKey(@NonNull final String key, @NonNull final String hashKey) {
        return Boolean.TRUE.equals(redisTemplate.opsForHash().hasKey(key, hashKey));
    }

    /**
     * 缓存递增
     *
     * @param key     键
     * @param hashKey hash键
     * @param delta   递增因子
     * @return Long 递增后的值
     */
    public Long hIncrement(@NonNull final String key, @NonNull final String hashKey, final long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 缓存递减
     *
     * @param key     键
     * @param hashKey hash键
     * @param delta   递减因子
     * @return Long 递减后的值
     */
    public Long hDecrement(@NonNull final String key, @NonNull final String hashKey, final long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    // ================================ Set ===================================

    /**
     * 获取set缓存
     *
     * @param key 键
     * @return Set<Object> 值
     */
    public Set<Object> sGet(@NonNull final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置set缓存
     *
     * @param key    键
     * @param values 值
     * @return Long 成功个数
     */
    public Long sSet(@NonNull final String key, @NonNull final Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 设置set缓存
     *
     * @param key     键
     * @param timeout 过期时间（秒）
     * @param values  值
     * @return Long 成功个数
     */
    public Long sSet(@NonNull final String key, final long timeout, @NonNull final Object... values) {
        final Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, timeout);
        return count;
    }

    /**
     * 删除set缓存
     *
     * @param key    键
     * @param values 值
     * @return Long 删除的数量
     */
    public Long sDelete(@NonNull final String key, @NonNull final Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断set缓存是否存在
     *
     * @param key   键
     * @param value 值
     * @return boolean true：存在，false：不存在
     */
    public boolean sHasKey(@NonNull final String key, @NonNull final Object value) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
    }

    // ================================ List ===================================

    /**
     * 获取list缓存
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置，-1：代表所有值
     * @return List<Object> 值
     */
    public List<Object> lGet(@NonNull final String key, final long start, final long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存长度
     *
     * @param key 键
     * @return Long 长度
     */
    public Long lGetListSize(@NonNull final String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获取list缓存中的值
     *
     * @param key   键
     * @param index 索引，从0开始，0：代表第一个元素，-1：代表最后一个元素
     * @return Object 值
     */
    public Object lGetIndex(@NonNull final String key, final long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 设置list缓存
     *
     * @param key    键
     * @param values 值
     * @return Long 成功个数
     */
    public Long lSet(@NonNull final String key, @NonNull final Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 设置list缓存
     *
     * @param key     键
     * @param timeout 过期时间（秒）
     * @param values  值
     * @return Long 成功个数
     */
    public Long lSet(@NonNull final String key, final long timeout, @NonNull final Object... values) {
        final Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, timeout);
        return count;
    }

    /**
     * 根据索引修改list缓存中的值
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lUpdateIndex(@NonNull final String key, final long index, @NonNull final Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 删除list缓存中与value相等的值
     *
     * @param key   键
     * @param count 删除的数量，count > 0：从左往右删除，count < 0：从右往左删除，count = 0：删除所有
     * @param value 值
     * @return Long 删除的数量
     */
    public Long lDelete(@NonNull final String key, final long count, @NonNull final Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
