package com.fuya.Redis;

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
    RedisProperties redisProperties;
    @Bean
    public JedisCluster getJedisCluster(){
        String[]serverArray=redisProperties.getClusterNodes().split(",");
        Set<HostAndPort>nodes=new HashSet<>();
        for (String port:serverArray){
            String[]isPortPair=port.split(":");
            nodes.add(new HostAndPort(isPortPair[0].trim(),Integer.valueOf(isPortPair[1].trim())));
        }
        return  new JedisCluster(nodes,redisProperties.getCommandTimeout());


    }

}
