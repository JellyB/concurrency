package com.mmall.concurrency.example.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-10-03 10:02 PM
 **/

@Slf4j
@Controller
@RequestMapping(value = "hystrix3")
@DefaultProperties(defaultFallback = "defaultFail")
public class HystrixController3 {


    @HystrixCommand(commandProperties = {
            //熔断器在整个统计时间内是否开启阈值
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
            //至少有3个请求才进行熔断错误比率计算
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "3"),
            //当出错率超过50%后熔断器启动
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
            //熔断器工作期间，超过这个时间，先放一个请求进去，成功的话就关闭熔断器，失败就在等一段时间
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
            //统计滚动的时间窗口
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000"),
    })
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(@RequestParam(value = "id") Integer id){
        log.info("id:{}", id);
        if(id % 2 == 0){
            throw new RuntimeException();
        }
        return "test_" + id;

    }

    private String defaultFail(){
        log.warn("default fail");
        return "default fail ~";
    }
}
