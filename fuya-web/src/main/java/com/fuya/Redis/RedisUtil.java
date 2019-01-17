package com.fuya.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisUtil {
    @Autowired
    private JedisCluster jedisCluster;
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }
    public boolean hasKey(String key) {
        return jedisCluster.exists(key);
    }

    public void setWithExpireTime( String key, String value, int expireTime) {
        jedisCluster.setex(key, expireTime, value);

    }
    public String get(String key) {
        String value = jedisCluster.get(key);
        return value;
    }
    public void delete(String key) {
        jedisCluster.del(key);
    }








}
