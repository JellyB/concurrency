package com.mmall.concurrency.example.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-16 6:10 PM
 **/

@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {TopicConstants.TEST})
    private void receiver(ConsumerRecord<?,?> record){
        log.info("receiver msg:{}", record);
    }
}
