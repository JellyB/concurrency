package com.mmall.concurrency.example.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-17 2:58 PM
 **/

@Component
@Slf4j
public class RabbitMQServer {

    @RabbitListener(queues = QueueConstants.TEST)
    private void receive(String message){
        log.info("{}", message);
    }
}
