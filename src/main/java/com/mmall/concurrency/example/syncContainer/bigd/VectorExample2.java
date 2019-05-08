package com.mmall.concurrency.example.syncContainer.bigd;

import com.mmall.concurrency.annoations.NotThreadSafe;

import java.util.List;
import java.util.Vector;

/**
 * 描述：
 *
 * @author biguodong
 * Create time 2019-05-07 6:03 PM
 **/

@NotThreadSafe
public class VectorExample2 {

    private static final Vector<Integer> vector
            = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 10; i ++){
                vector.add(i);
            }
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });
            thread1.start();
            thread2.start();
        }
    }
}
