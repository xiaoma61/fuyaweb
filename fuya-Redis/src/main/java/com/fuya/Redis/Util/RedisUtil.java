package com.fuya.Redis.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Set;

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
    public Long llen(String key){
        return jedisCluster.llen(key);
    }
    public void delete(String key) {
        jedisCluster.del(key);
    }
    public void lpush(String key,String value){
        jedisCluster.lpush(key,value);
    }
    public List<String>brpop(int timeout,String key){
        return jedisCluster.brpop(timeout,key);
    }
    public void zSet(String key,String value){
        jedisCluster.sadd(key,value);
    }
    public Set<String> smembers(String key){
        Set<String> smembers = jedisCluster.smembers(key);
        return smembers;
    }
    public Set<String> zrevrangeByScore(String name,int start,int rows){

        return jedisCluster.zrevrangeByScore(name, "+inf", "-inf",start,rows);
    }
    public void zadd(String key,int j,String name){
        jedisCluster.zadd(name, j, key);
    }
    public boolean sismember (String key,String value){
        return jedisCluster.sismember(key,value);
    }
    public Long sadd(String key, String value){
        return jedisCluster.sadd(key,value);
    }
    public boolean hexists(String key,String field){
        return  jedisCluster.hexists(key, field);
    }
    public String hGet(String key,String field){
        return jedisCluster.hget(key,field);
    }

    public Long hset(String key , String field, String value){
        return jedisCluster.hset(key, field, value);
    }



}
