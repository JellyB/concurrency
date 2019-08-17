package com.mmall.concurrency.example.mq.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-17 2:45 PM
 **/

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
        return new Queue(QueueConstants.TEST);
    }
}
