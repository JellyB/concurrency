package com.mmall.concurrency.example.threadLocal.bigd;

import com.mmall.concurrency.example.threadLocal.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-06 6:12 PM
 **/


@Controller
@RequestMapping("/threadLocal-bigd")
public class ThreadLocalControllerBigd {

    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }
}
