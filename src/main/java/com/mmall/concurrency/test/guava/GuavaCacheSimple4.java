package com.mmall.concurrency.test.guava;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-11-08 4:48 PM
 **/

@Slf4j
public class GuavaCacheSimple4 {


    // 还有一个问题不爽：真正加载数据的那个线程一定会阻塞，我们希望这个加载过程是异步的
    // 这样就可以让所有线程立马返回旧值，在后台刷新缓存数据
    // refreshAfterWrite 默认的刷新是同步的，会在调用者的线程中执行
    // 我们可以改造成异步的，实现 CacheLoader.reload()


    private static int THREAD_LOAD = 10;
    private static String KEY = "name";
    private static String VALUE  = "bigDev";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    // 模拟一个需要耗时 2s 的数据库查询任务
    private static Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(2000);
            String data = UUID.randomUUID().toString().replace("-", "");
            SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
            System.out.println(format.format(new Date()) +"    >                  > 2. 数据库数据读入到缓存中:_ " + data);
            return data;
        }
    };

    // guava 线程池， 用来产生 ListenableFuture
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));

    // 1s 后刷线缓存
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(1000, TimeUnit.MILLISECONDS)
            .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return callable.call();
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
                        System.out.println(format.format(new Date()) + "    >                  > 1. 线程异步刷新数据库数据 oldValue:_  " + oldValue);
                        return service.submit(callable);
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

        for (int i = 0; i < 5 * THREAD_LOAD; i++) {
            int sleep = 200 * i;
            startNewThreadAgin(sleep, i);
        }
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

    // 启动任务获取数据
    private static void startNewThreadAgin(int sleep, int i){
        Thread thread = new Thread(() ->{
            try{
                TimeUnit.MILLISECONDS.sleep(2000);
                TimeUnit.MILLISECONDS.sleep(sleep);
                SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
                String name = Thread.currentThread().getName();

                System.out.println(format.format(new Date()) + "    " +name + "         get.value:      " + cache.get(KEY));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.setName("Cache:    " + i + "    ");
        thread.start();
    }
}
