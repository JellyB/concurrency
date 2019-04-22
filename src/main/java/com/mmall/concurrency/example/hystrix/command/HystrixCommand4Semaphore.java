package com.mmall.concurrency.example.hystrix.command;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-22 5:12 PM
 **/
@Slf4j
public class HystrixCommand4Semaphore extends HystrixCommand<String>{

    private final String name;


    public HystrixCommand4Semaphore(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("semaphoreTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("semaphoreTestCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("semaphoreTestThreadPoolKey"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //信号量隔离
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // 最大信号量 = 5
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(5)
                        // 降级并发量 = 1，改为 2 后不会出现异常（降级可以控制并发量）
                        /**
                         * 不能无限降级，如果QPS 非常高不对降级控制的话，降级可能把你降级逻辑打爆
                         */
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(2)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(10);
        return "------- normal -----------" + name;
    }

    @Override
    protected String getFallback() {
        try{
            TimeUnit.MILLISECONDS.sleep(10);
            return "----------fallback --------" + name;
        }catch (Exception e){
           log.error(e.getMessage());
           return e.getMessage();
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger integer = new AtomicInteger(0);
        log.info("---------------------------------------------------");
        for (; integer.get() < 10; integer.incrementAndGet()) {
            executorService.execute(()-> {
                try {
                    Future<String> future = new HystrixCommand4Semaphore("" + integer.get()).queue();
                    log.info(">>>>>>>>>>>>>>>>>>>>{}", future.get());
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }
        TimeUnit.SECONDS.sleep(3);
    }
}
