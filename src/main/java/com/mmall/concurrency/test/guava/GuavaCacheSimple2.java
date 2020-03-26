package com.mmall.concurrency.test.guava;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-11-08 2:34 PM
 **/
public class GuavaCacheSimple2 {

    // 在上一个 example1 中，恰好有多个线程读取同一个 key 的值，那么 guava 只允许一个线程去加载数据，其余线程阻塞。
    // 这虽然可以防止大量请求穿透缓存，但是效率低下。使用 expireAfterWrite 可以做到：
    // 只阻塞加载数据的线程，其余线程返回旧数据。

    private static int THREAD_LOAD = 10;
    private static String KEY = "name";
    private static String VALUE  = "毕经验之谈";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    // 模拟一个需要耗时 2s 的数据库查询任务
    private static Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            System.out.println("begin to mock query db....");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("success to mock query db...");
            return UUID.randomUUID().toString();
        }
    };

    // 1s 后刷线缓存
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return callable.call();
        }
    });

    public static void main(String[] args) throws Exception{

        // 手动添加一条缓存数据，睡眠 1.5s 让其过期
        cache.put(KEY, VALUE);
        TimeUnit.MILLISECONDS.sleep(1500);

        for (int i = 0; i < THREAD_LOAD; i++) {
            startNewThread(i);
        }

        // 放行线程
        countDownLatch.countDown();
    }


    // 启动任务获取数据
    private static void startNewThread(int i){
        Thread thread = new Thread(() ->{
            try{
                String name = Thread.currentThread().getName();
                System.out.println(name + "    begin");
                countDownLatch.await();
                Stopwatch watch = Stopwatch.createStarted();
                System.out.println(name + "         get.value:      " + cache.get(KEY));
                watch.stop();
                System.out.println(name + "                 finish.costTime:      " + watch.elapsed(TimeUnit.SECONDS));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.setName("User:    " + i + "    ");
        thread.start();
    }
}
