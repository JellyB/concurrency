package com.mmall.concurrency.example.aqs.bigd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 描述：任务 - 加和计算，继承 java 递归任务类 RecursiveTask
 * @author biguodong
 **/
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer>{

    //计数起始值
    private int start;
    //计数结束值
    private int end;
    //计算阈值
    private static final int threshold = 2;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     *  重写父类方法，fork 和 join 操作的执行逻辑
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小不拆分直接计算任务，最开始的时候是 100 - 1
        boolean canCompute = (end - start) <= threshold;
        if(canCompute){
            for(int i = start; i <= end; i ++){
                sum += i;
            }
        }else{

            /**
             * 如果任务大于阈值，就拆分成两个子任务（两条线）计算，两条线去递归执行子任务任务拆分到
             * 什么情况下，取决于我们上述的 canCompute 是否成立
             * 相当于使用递归的方式把一个大任务拆分成若干个子任务
             */
            int middle = (end + start) / 2;
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待任务执行结束 -> 合并其结果
            int leftValue = leftTask.join();
            int rightValue = rightTask.join();
            sum = leftValue + rightValue;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /**
         * 提交一个计算任务计算从 1 相加到 100 的和，会执行我们覆盖的 RecursiveTask（递归任务）的 compute 方法
         */
        Future<Integer> sum = forkJoinPool.submit(task);
        try{
            log.info("result:{}", sum.get());
        }catch (Exception e){
            log.error("error:{}", e);
        }
    }
}
