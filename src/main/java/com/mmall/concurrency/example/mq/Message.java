package com.mmall.concurrency.example.mq;

import lombok.Data;

import java.util.Date;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-08-17 1:59 PM
 **/

@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
