package com.mmall.concurrency.example.mq.kafka;

import com.alibaba.fastjson.JSONObject;
import com.mmall.concurrency.example.mq.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-16 6:10 PM
 **/

@Component
@Slf4j
public class KafkaSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    public void send(String msg){
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("send msg:{}", message);
        kafkaTemplate.send(TopicConstants.TEST, JSONObject.toJSONString(message));
    }
}
