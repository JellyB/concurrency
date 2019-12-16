/**
 * Copyright 2014-2025 JD.COM All Right Reserved.
 *
 * @project: YX_RabbitMQ
 * @file: FeiBo.java
 * @creator: biguodong
 * @version:
 * @date: 2019/12/16 10:56
 */
package com.mmall.concurrency.interview.fibo;

public class FiBo {

    private static final Integer ignore = 8;
    private static String zero = "0";
    private static String first = "1";
    private static String second = "1";

    private String numA;
    private String numB;
    private int index;

    public String getNumA() {
        return numA;
    }

    public void setNumA(String numA, Integer ignore) {
        if(null != ignore && numA.length() >= ignore){
            numA = numA.substring(numA.length() - ignore, numA.length());
        }
        this.numA = numA;
    }

    public String getNumB() {
        return numB;
    }

    public void setNumB(String numB, Integer ignore) {
        if(null != ignore && numB.length() >= ignore){
            numB = numB.substring(numB.length() - ignore, numB.length());
        }
        this.numB = numB;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public FiBo(String numA, String numB, int index) {
        this.numA = numA;
        this.numB = numB;
        this.index = index;
    }

    public static String getValue(FiBo fiBo){
        return BigNumber.add(fiBo.getNumA(), fiBo.getNumB());
    }

    public static FiBo index (int index) {
        if(index == 1){
            return new FiBo(zero, first, 1);
        }else if(index == 2){
            return new FiBo(zero, first, 2);
        }else{
            FiBo fiBo = new FiBo(first, second, 3);
            if(index == fiBo.getIndex()){
                return fiBo;
            }
            do{
                String numCStr = BigNumber.add(fiBo.getNumA(), fiBo.getNumB());
                //System.err.println(numCStr);
                fiBo.setNumA(fiBo.getNumB(), ignore);
                fiBo.setNumB(numCStr, ignore);
                fiBo.setIndex(fiBo.getIndex() + 1);
            }while(index != fiBo.getIndex());
            return fiBo;
        }
    }

    public static void main(String[] args) {

        System.err.println("fabioValue:  " + getValue(FiBo.index(1)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(2)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(3)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(4)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(5)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(6)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(7)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(8)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(9)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(5000)));
        System.err.println("fabioValue:  " + getValue(FiBo.index(10000)));
        //System.err.println(FeiBo.index(300));
    }
}
