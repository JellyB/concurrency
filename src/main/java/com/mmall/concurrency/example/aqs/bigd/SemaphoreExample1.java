package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述： 正确的写法
 *
 * @author biguodong
 * Create time 2019-05-10 5:19 PM
 **/
@Slf4j
public class SemaphoreExample1 {
    //模拟任务数
    private static final Integer threadCount = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i ++){
            executorService.execute( () -> {
                try{
                    semaphore.acquire(); // 获取一个许可
                    test();
                    semaphore.release(); // 释放一个许可
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        //线程池用完即关
        executorService.shutdown();
    }

    private static void test() throws InterruptedException{
        TimeUnit.SECONDS.sleep(1);
        log.info("current thread.no {}", Thread.currentThread().getId());
    }
}
