package com.mmall.concurrency.example.hystrix.command;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 描述：断路器样例
 *
 * @author biguodong
 * Create time 2019-04-22 5:50 PM
 **/
@Slf4j
public class HystrixCommandCircuitBreaker extends HystrixCommand<String> {


    private final String name;
    public HystrixCommandCircuitBreaker(String name) {

        /**
         * CircuitBreakerRequestVolumeThreshold 设置为 5 意味着10s内请求超过 3次就触发熔断器
         * run() 中无限循环使命令超时进入 fallback 执行 10 次run 后 将被 熔断，进入降级，既不进入 run(),而直接进入fallback
         * 如果未熔断，但是 threadPool 被打满，仍然会降级，既不进入 run(),而直接进入fallback
         */
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("circuitBreakerTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("circuitBreakerTestCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("circuitBreakerTestThreadPoolKey"))
                // 配置线程池大小，防止未熔断却打满线程池而触发降级
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(200))
                .andCommandPropertiesDefaults(
                        //配置熔断器
                        HystrixCommandProperties.Setter()
                        //时间窗口
                        .withCircuitBreakerSleepWindowInMilliseconds(10000)
                        //错误率达到 50%，熔断打开，请求不会再进来
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        //打开熔断器
                        .withCircuitBreakerEnabled(true)
                        // 10s 内开始统计熔断信息，如果 10s 内发生了 5 个请求就开始计算熔断逻辑
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        //.withCircuitBreakerForceOpen(true) //如果置为 true 所有请求都将被拒绝，直接到fallback
                        //.withCircuitBreakerForceClosed(true) 如果置为 true 将忽略错误
                         ));

        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        int value = Integer.valueOf(name);
        //正常逻辑
        if(value % 2 == 0 && value < 10){
            return "---------   normal: " + name;
        }else{
            //模拟异常
            while (true){

            }
        }
    }

    @Override
    protected String getFallback() {
        return "---------   fallback " + name;
    }

    public static void main(String[] args) throws Exception{
        //提交 50个
        for(int i = 0; i < 50; i ++){
            try{
                String result = new HystrixCommandCircuitBreaker(String.valueOf(i)).execute();
                log.info(result);
            }catch (Exception e){
                log.error("请求出错了:{}", e.getMessage());
            }
        }
        log.info("----------------打印现有线程-------------------");
        Map<Thread, StackTraceElement[]> threadMap =  Thread.getAllStackTraces();
        for (Thread thread : threadMap.keySet()) {
            log.info(">>>>>>>>:{}", thread.getName());
        }
        log.info("thread num:{}", threadMap.keySet().size());
    }
}
