package com.mmall.concurrency;

/**
 * 描述：复利计算
 *
 * @author biguodong
 * Create time 2019-05-24 4:40 PM
 **/


import lombok.extern.slf4j.Slf4j;

/**
 * 假设两人都是每月定投1000元，每年的收益率为12%

   小明从20岁开始投资，共投资10年，到31岁的时候就不再投入本金。

   小强从25岁开始投资，一直投入本金直到60岁。

    我们来看一下，当他们到60岁的时候各自都有多少钱呢？
 */
@Slf4j
public class CompoundTest {
    private static final double base = 1000;
    private static double Xm;
    private static double Xq;
    public static void main(String[] args) {

        testXm();
        log.info("---------------------------------------");
        testXq();

        log.info("小明总共投入:{}, 小强总共投入:{}", new Double(Xm).intValue(), new Double(Xq).intValue());
    }

    private static void  testXm(){
        double currentYear = base * 12;
        double sum = 0;
        for(int i = 20; i < 31; i ++){
            sum = (sum +  currentYear) * 1.12;
            log.info("每月定投    -----  小明第：{} 岁有:{}", i, new Double(sum).intValue());
        }

        for(int i = 31; i < 61; i ++){
            sum = sum * 1.12;
            log.info("开始不定投后 ----- 小明 :{} 岁有:{}", i, new Double(sum).intValue());
        }
        Xm = currentYear * 10;
    }

    private static void testXq(){
        double currentYear = base * 12;
        double sum = 0;
        for(int i = 25; i < 61; i ++){
            sum = (sum +  currentYear) * 1.12;
            log.info("每月定投  ---  小强：{} 岁有:{}", i, new Double(sum).intValue());
        }
        Xq = currentYear * 30;
    }
}
