package com.mmall.concurrency.example.concurrent.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-08 3:19 PM
 **/
@Slf4j
public class CopyOnWriteArrayListExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i ++){
            final int count = i;
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (InterruptedException e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list.size:{}", list.size());
    }

    private static void update(Integer i){
        list.add(i);
    }
}
