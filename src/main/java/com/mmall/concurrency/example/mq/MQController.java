package com.mmall.concurrency.example.mq;

import com.mmall.concurrency.example.mq.kafka.KafkaSender;
import com.mmall.concurrency.example.mq.rabbitmq.RabbitMQClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-17 3:02 PM
 **/

@Controller
@RequestMapping("/mq")
public class MQController {

    @Resource
    private KafkaSender kafkaSender;

    @Resource
    private RabbitMQClient rabbitMQClient;

    @RequestMapping(value = "/send")
    @ResponseBody
    public String send(@RequestParam(value = "message") String message){
        rabbitMQClient.send(message);
        kafkaSender.send(message);
        return "SUCCESS";
    }
}
