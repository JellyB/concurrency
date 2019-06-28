package com.mmall.concurrency.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-06-12 8:39 PM
 **/
@Slf4j
public class GuavaCacheExample2 {

    public static void main(String[] args) {

        Cache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)    // 1. 最多存放 10 个数据
                //.maximumWeight(1)   // 2.
                .expireAfterWrite(10, TimeUnit.SECONDS)  // 1. 缓存 10 秒(缓存策略)
                //.expireAfterAccess(10, TimeUnit.SECONDS) //2.
                .recordStats()      // 开启记录状态数据功能
                .build();

        //log.info("{}", cache.get("key1"));                // exception
        log.info("{}", cache.getIfPresent("key1"));     // null
        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1"));     // 1
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1"));     // null
        try{
            log.info("{}", cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            }));              // -1
            cache.put("key2", 2);
            log.info("{}", cache.get("key2", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            }));              // 2
            log.info("{}", cache.size());                   // 1
            for(int i = 3; i < 13; i ++){
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size());                   // 10

            log.info("{}", cache.getIfPresent("key2")); // null

            TimeUnit.SECONDS.sleep(10);

            log.info("{}", cache.get("key5", new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return -1;
                }
            }));              // -1
            log.info("{},{}", cache.stats().hitCount(), cache.stats().missCount());
            log.info("{},{}", cache.stats().hitRate(), cache.stats().missRate());
        }catch (Exception e){

        }
    }
}
