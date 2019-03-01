package com.mmall.concurrency.example.publish.bigd;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-03-01 10:58 PM
 **/

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape{

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }


    public class InnerClass{
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
