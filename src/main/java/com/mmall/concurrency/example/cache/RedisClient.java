package com.mmall.concurrency.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-06-28 5:21 PM
 **/


// http://redis.cn/
@Slf4j
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception{
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
