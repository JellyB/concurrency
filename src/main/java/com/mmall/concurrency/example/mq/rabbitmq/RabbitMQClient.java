package com.mmall.concurrency.example.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-17 2:49 PM
 **/

@Slf4j
@Component
public class RabbitMQClient {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend(QueueConstants.TEST, message);
    }
}
