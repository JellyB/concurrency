package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-13 6:13 PM
 **/
@Slf4j
public class CyclicBarrierExample3 {

    private static final CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        log.info("all thread is ready. call back is execute");
    });

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            final int threadNum = i;
            executorService.execute(() -> {
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception");
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        TimeUnit.MILLISECONDS.sleep(500);
        log.info("thread num:{} is ready", threadNum);
        barrier.await();
        log.info("continue:{}", threadNum);

    }
}
