package com.mmall.concurrency.example.publish.bigd;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-03-01 10:39 PM
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] status = {"a", "b", "c"};

    public String[] getStatus(){
        return status;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("states:{}", Arrays.toString(unsafePublish.getStatus()));

        unsafePublish.getStatus()[0] = "d";
        log.info("states:{}",  Arrays.toString(unsafePublish.getStatus()));
    }
}
