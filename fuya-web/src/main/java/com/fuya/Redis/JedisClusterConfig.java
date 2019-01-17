package com.fuya.Redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisClusterConfig {
    @Autowired
    private  RedisProperties redisProperties;
    @Bean
    public JedisCluster getJedisCluster(){
        String[] serverArray=redisProperties.getClusterModes().split(",");
        //Set 没有相同的value
        //map 没有相同的键，允许为null 非synchronized的，但是

        //trim 去掉空格
        //
        Set<HostAndPort>nodes=new HashSet<>();
        for(String port:serverArray){
            String[] ipPortPair=port.split(":");
            //前面ip，后面端口
             nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes,redisProperties.getCommandTimeout(),1000,1,redisProperties.getPassword() ,new GenericObjectPoolConfig());
    }



}
