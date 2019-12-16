/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @project: YX_RabbitMQ
 * @file: FiboApp.java
 * @creator: biguodong
 * @version: 1
 * @date: 2019/12/16 15:55
 */
package com.mmall.concurrency.interview.fibo;

public class FiboApp {

    private final static Integer ignore = 8;
    private int index;

    private FiBo fiBo;

    private String sum;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public FiBo getFiBo() {
        return fiBo;
    }

    public void setFiBo(FiBo fiBo) {
        this.fiBo = fiBo;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum, Integer ignore) {
        if(null != ignore && sum.length() >= ignore){
            sum = sum.substring(sum.length() - ignore, sum.length());
        }
        this.sum = sum;
    }

    public FiboApp(int index, FiBo fiBo, String sum) {
        this.index = index;
        this.fiBo = fiBo;
        this.sum = sum;
    }

    public static String sum(int index, boolean format){
        int cur = 1;
        FiboApp fiboApp = new FiboApp(index, null, "0");
        while(cur <= index){
            FiBo fiBo = FiBo.index(cur);
            String sum = BigNumber.add(FiBo.getValue(fiBo), fiboApp.getSum());
            fiboApp.setSum(sum, ignore);
            cur ++;
        }
        if(format){
            return String.format("%08d", Long.parseLong(fiboApp.getSum()));
        }else{
            return fiboApp.getSum();
        }
    }

    public static void main(String[] args) {
        System.err.println("sum6  " + FiboApp.sum(6, true));
        System.err.println("sum16  " + FiboApp.sum(16, true));
        System.err.println("sum116  " + FiboApp.sum(116, true));
        System.err.println("sum5000  " + FiboApp.sum(5000, true));
        System.err.println("sum10000  " + FiboApp.sum(10000, true));
    }
}
