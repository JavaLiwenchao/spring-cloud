package com.lwc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by liwenchao on 2018-05-28.
 *
 * @author liwenchao
 */
@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key, String value) {
        ValueOperations<String, String> opsForValue =
                stringRedisTemplate.opsForValue();
        opsForValue.set(key, value, 1, TimeUnit.MINUTES);
    }

    public String getValue(String key) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.get(key);
    }
}
