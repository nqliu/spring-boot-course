package top.nql.boot.cache;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {
<<<<<<< HEAD
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;
    public final static long NOT_EXPIRE = -1L;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
=======
    /**
     * 默认过期时长为24小时，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**
     * 过期时长为1小时，单位：秒
     */
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    /**
     * 过期时长为6小时，单位：秒
     */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }
<<<<<<< HEAD
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }
=======

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
        return value;
    }
<<<<<<< HEAD
    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }
=======

    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public Map<String, Object> hGetAll(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }
<<<<<<< HEAD
    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }
    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);
=======

    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }

    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }
<<<<<<< HEAD
    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }
    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);
=======

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }

    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }
<<<<<<< HEAD
    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }
    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }
    public void leftPush(String key, Object value) {
        leftPush(key, value, DEFAULT_EXPIRE);
    }
    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);
=======

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public void leftPush(String key, Object value) {
        leftPush(key, value, DEFAULT_EXPIRE);
    }

    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }
<<<<<<< HEAD
=======

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
}