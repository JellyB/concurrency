package com.mmall.concurrency.test.guava;

import com.google.common.base.Stopwatch;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-11-08 2:34 PM
 **/


public class GuavaCacheSimple1 {

    // 设想高并发下的一种场景：假设我们将 name = aty 存放到缓存中，并设置过期时间
    // 当缓存过期后，恰巧有 10 个客户端发起请求，需要获取 name 的值，使用 Guava Cache 可以保证只让一个去加载数据（从 数据库中或其他），
    // 而其他线程则等待这个线程的返回结果。这样就能避免大量用户请求穿透缓存

    private static int THREAD_LOAD = 10;
    private static String KEY = "name";
    private static String VALUE  = "毕经之谈";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    // 1s 内没有访问则缓存过期，每次加载一个 key 需要耗时 2 s
    public static LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {

        @Override
        public String load(String key) throws Exception {
            System.out.println("begin to query from db...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("success load db from db...");
            return UUID.randomUUID().toString();
        }
    });

    //  下面的输出结果可以看到：只有一个线程去数据库中加载数据，其他线程都在等待（每个线程都耗时 2 s）
    //  使用 Guava 确实可以做到：
    //  对于同一个 key，无论有多少请求，都只允许一个线程去加载数据
    //  但是也有一个很致命的缺陷： 上面 10 个线程中，有一个线程实际去加载数据，其余 9 个线程都被阻塞了。
    //  如果能做到，当一个线程去加载数据，其余线程发现这个数据正在加载中，那么直接读取老的数据，这样就不会阻塞了。
    //  既然是缓存，读取旧一点的数据也没有多大问题，却可以提高系统吞吐量

    public static void main(String[] args) throws Exception{
        // 初始化 cache
        cache.put(KEY, VALUE);

        // 休眠两秒，让缓存过期
        TimeUnit.MILLISECONDS.sleep(2000);
        for(int i = 0 ; i < THREAD_LOAD; i ++){
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
