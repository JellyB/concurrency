package com.mmall.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-06-28 5:33 PM
 **/

@Controller
@RequestMapping(value = "cache")
public class CacheController {


    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get(@RequestParam(value = "k") String key) throws Exception{
        return redisClient.get(key);
    }



    @RequestMapping(value = "/set")
    @ResponseBody
    public String set(@RequestParam(value = "k") String key, @RequestParam(value = "v") String value)throws Exception{
        redisClient.set(key, value);
        return "SUCCESS";
    }
}
