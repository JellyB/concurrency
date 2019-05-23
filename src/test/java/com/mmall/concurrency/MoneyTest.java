package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.NumberUtils;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-16 11:52 AM
 **/
@Slf4j
public class MoneyTest {


    @Test
    public void test(){
        double rate = 0.03;
        double baseMoney = 1000;
        int supperMoney = 5000;
        double sum = 0;
        for (int i = 1; i < 18; i ++){
            double currentMonthMoney = 1000 * Math.pow(1.1, i);
            sum += currentMonthMoney * 12;
            sum = sum * (1 + rate);
            log.info(">>>>>>>>> 第:{}年，每月需要投入:{}，共有:{}钱", i, new Double(currentMonthMoney).intValue(), new Double(sum).intValue());
        }
        for(int i = 18; i <= 30; i ++){
            int supperSum = supperMoney * 12;
            sum = sum + supperSum;
            sum = sum * (1 + rate);
            log.info(">>>>>>>>> 第:{}年，每月需要投入:{}，共有:{}钱", i, supperMoney,  new Double(sum).intValue());
        }
    }
}
