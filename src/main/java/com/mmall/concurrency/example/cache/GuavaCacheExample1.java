package com.mmall.concurrency.example.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-06-12 8:39 PM
 **/
@Slf4j
public class GuavaCacheExample1 {

    public static void main(String[] args) {

        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)    // 1. 最多存放 10 个数据
                //.maximumWeight(1)   // 2.
                .expireAfterWrite(10, TimeUnit.SECONDS)  // 1. 缓存 10 秒(缓存策略)
                //.expireAfterAccess(10, TimeUnit.SECONDS) //2.
                .recordStats()      // 开启记录状态数据功能
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return -1;
                    }
                });

        //log.info("{}", cache.get("key1"));                // exception
        log.info("{}", cache.getIfPresent("key1"));     // null
        cache.put("key1", 1);
        log.info("{}", cache.getIfPresent("key1"));     // 1
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1"));     // null
        try{
            log.info("{}", cache.get("key2"));              // -1
            cache.put("key2", 2);
            log.info("{}", cache.get("key2"));              // 2
            log.info("{}", cache.size());                   // 1
            for(int i = 3; i < 13; i ++){
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size());                   // 10

            log.info("{}", cache.getIfPresent("key2")); // null

            TimeUnit.SECONDS.sleep(10);

            log.info("{}", cache.get("key5"));              // -1
            log.info("{},{}", cache.stats().hitCount(), cache.stats().missCount());
            log.info("{},{}", cache.stats().hitRate(), cache.stats().missRate());
        }catch (Exception e){

        }
    }
}
