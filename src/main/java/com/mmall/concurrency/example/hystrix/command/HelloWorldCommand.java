package com.mmall.concurrency.example.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-04-22 4:04 PM
 **/
@Slf4j
public class HelloWorldCommand extends HystrixCommand<String>{

    private final String name;

    public HelloWorldCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("helloWorldCommand"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "hello " + name + "thread name " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception{
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("同步调用获取结果");
        log.info("----------------------------------------------------------------------");
        String result = helloWorldCommand.execute();
        log.info("同步 result >>>   {}", result);


        helloWorldCommand = new HelloWorldCommand("异步调用获取结果");
        Future<String> future = helloWorldCommand.queue();
        // future.get() 阻塞的过程
        log.info("异步 result >>>    {}", future.get(100, TimeUnit.MILLISECONDS));
        log.info("main Thread:{}", Thread.currentThread().getName());

    }
}
