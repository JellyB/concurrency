package com.mmall.concurrency.example.syncContainer.bigd;

import java.util.Iterator;
import java.util.Vector;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-08 9:39 AM
 **/
public class VectorExample3 {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        foreachTest(vector);
        //iteratorTest(vector);
        //forTest(vector);
    }

    private static void foreachTest(Vector<Integer> vector){
        for (Integer item : vector) {
            if(item.equals(3)){
                vector.remove(item);
            }
        }
    }

    private static void iteratorTest(Vector<Integer> vector){
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()){
            Integer current =  iterator.next();
            if(current.equals(3)){
                vector.remove(current);
            }
        }
    }


    private static void forTest(Vector<Integer> vector){
        for (int i = 0; i < vector.size(); i ++){
            Integer current = vector.get(i);
            if(current.equals(3)){
                vector.remove(i);
            }
        }
    }
}
