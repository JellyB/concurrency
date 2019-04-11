package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-11 6:18 PM
 **/
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            TimeUnit.SECONDS.sleep(5);
            return "task done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future= executorService.submit(new MyCallable());
        log.info("do something in main");
        TimeUnit.SECONDS.sleep(1);
        String result = future.get();
        log.info("result:{}", result);
    }
}
