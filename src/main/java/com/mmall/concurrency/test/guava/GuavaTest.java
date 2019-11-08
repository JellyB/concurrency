package com.mmall.concurrency.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-11-07 10:39 AM
 **/
@Slf4j
public class GuavaTest {

    private static final Cache<Integer, String> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).recordStats().build();

    public static void main(String[] args) {
        new Thread(()-> {
            while(true){
                log.info("size:{}", cache.size());
            }
        }).start();

        int count = 0;
        for(;;){
            final int j = count % 20;
            try{
                String result = cache.get(j, new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        //System.err.println("------------ :" + j + "没有请求缓存");
                        return newStr(j);
                    }
                });
                System.err.println("------------ :"  + result);
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            count ++;
        }
    }

    private static String newStr(int count){
        return "" + count;
    }
}
