package com.mmall.concurrency.example.hystrix.command;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-22 4:31 PM
 **/
@Slf4j
public class HystrixCommand4ThreadPool extends HystrixCommand<String>{

    private final String name;

    public HystrixCommand4ThreadPool(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("threadPoolTestGroup"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPoolKey"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(5000))
        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties
                .Setter()
                .withCoreSize(5))); // 配置 hystrix 线程池里面的核心线程数
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(2000);
        log.info("----------- normal ------------ 线程名 :{}, 对象名 :{}", Thread.currentThread().getName(), name);
        return name;
    }

    @Override
    protected String getFallback() {
        return "----------- fallback ------------" + name;
    }


    public static void main(String[] args) throws Exception{
        for(int i = 0 ; i < 5 ; i ++){

            //阻塞执行，看不出效果
            //Future<String> result = new HystrixCommand4ThreadPool("available thread test  " + i).queue();
            //log.info("第一波线程池线程执行结果:{}", result.get());

            // 异步执行
            new HystrixCommand4ThreadPool("available thread test  " + i).queue();
        }
        // 走降级的 10 个线程
        /**
         * 这里注意，如果有线程被执行，就是如果我们配置的 coreSize < 第一波线程池配置，效果不是这样的
         */
        for(int i = 0 ; i < 10 ; i ++){
            Future<String> result = new HystrixCommand4ThreadPool("No available thread test  " + i).queue();
            log.info("第二波线程池线程执行结果:{}", result.get());
        }
        // 主线程等待 2000 等待 前 5 个线程执行完毕，输出结果
        TimeUnit.SECONDS.sleep(2000);
        log.info(" ------------------------正常线程执行结果打印：------------------");
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
        for (Thread thread : threadMap.keySet()) {
           log.info("----------------------:{}", thread.getName());
        }
    }
}
