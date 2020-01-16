package com.zm.cj.util;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private byte[] rawKey;

    private BoundListOperations<String, String> listOperations;//noblocking


    public void pushFromHead(String key, String value) {
        bindKeys(key);
        listOperations.leftPush(value);
    }

    public List<String> getList(String key) {
        bindKeys(key);
        List<String> range = listOperations.getOperations().opsForList().range(key, 0, -1);
        return range;
    }

    public Long removeFromList(String key, String value) {
        bindKeys(key);
        Long remove = listOperations.getOperations().opsForList().remove(key, 1, value);
        return remove;
    }

    public void ltrimFromList(String key, long index, String value) {
        bindKeys(key);
        listOperations.getOperations().opsForList().set(key, index, value);
    }

    public void pushFromTail(String key, String value) {
        bindKeys(key);
        listOperations.rightPush(value);
    }


    public String removeFromHead(String key) {
        bindKeys(key);
        return listOperations.leftPop();
    }


    public String removeFromTail(String key) {
        bindKeys(key);
        return listOperations.rightPop();
    }


    public Long getKeySize(String key) {
        bindKeys(key);
        return listOperations.size();
    }

    private void bindKeys(String key) {
        rawKey = redisTemplate.getKeySerializer().serialize(key);
        listOperations = redisTemplate.boundListOps(key);
    }


    public void saveMap(final String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public LinkedHashSet<String> getKeys(String keys) {
        Object o = redisTemplate.keys(keys + "*");
        if (Objects.nonNull(o))
            return (LinkedHashSet<String>) o;
        else
            return Sets.newLinkedHashSet();
    }

    public List<Object> mget(Collection<String> keys) {
        List list = stringRedisTemplate.opsForValue().multiGet(keys);
        return list;
    }

    public void saveMap(final String key, long expireTime, Map map) {
        saveMap(key, map);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public void set(final String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void set(final String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJSONString(value));
    }

    public void setNotEmpty(final String key, Object value, long expireSeconds) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJSONStringNotEmpty(value));
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    public void set(final String key, Object value, long expireSeconds) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJSONString(value));
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }


    @Async
    public void setAsync(final String key, String value, Long expireSeconds) {
        stringRedisTemplate.opsForValue().set(key, value);
        if (Objects.nonNull(expireSeconds))
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    public void set(final String key, Object value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJSONString(value));
        redisTemplate.expire(key, time, timeUnit);
    }

    @Async
    public void expire(final String key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public Long incr(final String key) {
        Long increment = redisTemplate.opsForValue().increment(key);
        return increment;
    }

    public Long decr(final String key) {
        Long decrement = redisTemplate.opsForValue().decrement(key);
        return decrement;
    }

    public void setForObject(final String key, Object value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    public void refreshTime(final String key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }


    public String get(String key) {
        if (Objects.isNull(stringRedisTemplate.opsForValue().get(key)))
            return null;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * @param key
     * @return 加锁
     */
    public boolean setLock(String key, String value, long seconds) {
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
        return aBoolean == null ? false : aBoolean;
    }

    public boolean deleteLike(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
        return true;
    }


    public Map getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object getMap(String key, String subKey) {
        return redisTemplate.opsForHash().get(key, subKey);
    }

    public boolean getMapExists(String key, String subKey) {
        return redisTemplate.opsForHash().hasKey(key, subKey);
    }

    public List getMapMultiKey(String key, List<String> subKeys) {
        return redisTemplate.opsForHash().multiGet(key, subKeys);
    }

    public Long sAdd(String key, String value) {
        Long add = stringRedisTemplate.opsForSet().add(key, value);
        return add;
    }

    public Set<String> sMembers(String key) {
        Set<String> members = stringRedisTemplate.opsForSet().members(key);
        return members;
    }

    public Long sRemove(String key, String value) {
        Long remove = stringRedisTemplate.opsForSet().remove(key, value);
        return remove;
    }

    public Boolean delete(String key) {
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }

    public void deleteMap(String key, String subKey) {
        redisTemplate.opsForHash().delete(key, subKey);
    }

    public boolean deleteAll(Set<String> key) {
        Long delete = redisTemplate.delete(key);
        return delete > 0;
    }

}
