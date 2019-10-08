package com.mmall.concurrency.example.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-03 9:56 PM
 **/

@Slf4j
@Controller
@RequestMapping(value = "hystrix1")
@DefaultProperties(defaultFallback = "defaultFail")
public class HystrixController1 {


    @HystrixCommand(fallbackMethod = "fail1")
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(){
        throw new RuntimeException();
    }

    private String fail1(){
        log.warn("fail1");
        return "fail1";
    }


    @HystrixCommand(fallbackMethod = "fail2")
    @RequestMapping(value = "/test2")
    @ResponseBody
    public String test2(){
        throw new RuntimeException();
    }

    @HystrixCommand(fallbackMethod = "fail3")
    private String fail2(){
        log.warn("fail2");
        throw new RuntimeException();
    }

    @HystrixCommand()
    private String fail3(){
        log.warn("fail3");
        throw new RuntimeException();
    }

    private String defaultFail(){
        log.warn("default fail");
        return "default fail ~";
    }
}
