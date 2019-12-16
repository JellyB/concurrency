/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @project: YX_RabbitMQ
 * @file: BigNumber.java
 * @creator: biguodong
 * @version: 1
 * @date: 2019/12/16 10:54
 */
package com.mmall.concurrency.interview.fibo;

import java.util.Stack;

public class BigNumber extends Stack {

    private String num;

    public BigNumber(String num) {
        for(int i = 0; i < num.length(); i ++){
            char c = num.charAt(i);
            this.push(Integer.parseInt(String.valueOf(c)));
        }
    }

    public BigNumber() {
    }

    public static String add(String a, String b){
        BigNumber numA = new BigNumber(a);
        BigNumber numB = new BigNumber(b);
        BigNumber sum = new BigNumber();
        int partialSum;
        boolean isCarry = false;
        while(!numA.isEmpty() && !numB.isEmpty()){
            partialSum = (Integer)numA.pop() + (Integer) numB.pop();
            if(isCarry){
                partialSum ++;
                isCarry = false;
            }
            if(partialSum >= 10){
                isCarry = true;
                partialSum-=10;
                sum.push(partialSum);

            }else{
                sum.push(partialSum);
            }
        }

        Stack temp = !numA.isEmpty() ? numA:numB;
        while(!temp.isEmpty()){
            int t = (Integer)temp.pop();
            if(isCarry){
                t++;
                isCarry = t >= 10;
                t = t >= 10 ? t-=10:t;
            }
            sum.push(t);
        }
        if(isCarry){
            sum.push(1);
        }
        return getValue(sum);
    }

    /**
     * 根据 bigNumber 获取字符串的值
     * @param bigNumber
     * @return
     */
    public static String getValue(BigNumber bigNumber){
        StringBuilder sumValue = new StringBuilder();
        while(!bigNumber.isEmpty()){
            sumValue.append(String.valueOf(bigNumber.pop()));
        }
        return sumValue.toString();
    }

    public static void main(String[] args) {
        /*BigNumber bigNumberA = new BigNumber("8");
        BigNumber bigNumberA_ = new BigNumber();
        bigNumberA_.addAll(bigNumberA);
        Collections.reverse(bigNumberA_);
        System.err.println(bigNumberA_);
        BigNumber bigNumberB = new BigNumber("13");
        BigNumber bigNumberB_ = new BigNumber();
        bigNumberB_.addAll(bigNumberB);
        Collections.reverse(bigNumberB_);
        System.err.println(bigNumberB_);
        Stack sum = add(bigNumberA, bigNumberB);
        //Collections.reverse(sum);
        StringBuilder sumValue = new StringBuilder();
        while (!sum.isEmpty()){
            sumValue.append(String.valueOf(sum.pop()));
        }
        System.err.println(sumValue.toString());*/
        System.err.println(BigNumber.add("99999999999999999999999999999", "1"));
        System.err.println(BigNumber.add("8", "13"));
    }


}
