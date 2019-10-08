package com.mmall.concurrency.example.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-03 10:02 PM
 **/

@Slf4j
@Controller
@RequestMapping(value = "hystrix2")
@DefaultProperties(defaultFallback = "defaultFail")
public class HystrixController2 {


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "500")
    } )
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1() throws Exception{
        TimeUnit.MILLISECONDS.sleep(1000);
        return "test1";
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "1500")
    }, threadPoolProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "30"),
            @HystrixProperty(name = HystrixPropertiesManager.MAX_QUEUE_SIZE, value = "101"),
            @HystrixProperty(name = HystrixPropertiesManager.KEEP_ALIVE_TIME_MINUTES, value = "2"),
            @HystrixProperty(name = HystrixPropertiesManager.QUEUE_SIZE_REJECTION_THRESHOLD, value = "15"),
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_NUM_BUCKETS, value = "12"),
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "1440"),
    })
    @RequestMapping(value = "/test2")
    @ResponseBody
    public String test2() throws Exception{
        TimeUnit.MILLISECONDS.sleep(1000);
        return "test2";
    }

    private String defaultFail(){
        log.warn("default fail");
        return "default fail ~";
    }
}
