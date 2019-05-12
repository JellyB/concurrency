package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-10 5:19 PM
 **/
@Slf4j
public class CountDownLatchExample1 {
    //模拟任务数
    private static final Integer threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i ++){
            final int threadNum = i;
            executorService.execute( () -> {
                try{
                    test(threadNum);
                }catch (Exception e){
                    log.error("exception", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finished");
        //线程池用完即关
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("threadNum --- {}", threadNum);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
