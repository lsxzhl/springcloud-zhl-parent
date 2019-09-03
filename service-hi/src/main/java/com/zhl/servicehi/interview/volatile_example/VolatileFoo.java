package com.zhl.servicehi.interview.volatile_example;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 16:59
 */
public class VolatileFoo {

    /**
     * init_value的最大值
     */
    final static int MAX = 5;

    /**
     * init_value的初始值
     */
    static volatile int init_value = 0;

    public static void main(String[] args) {
        //启动一个Reader线程，当发现local_value和init_value不同时，
        //则输出init_value被修改的信息
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    System.out.printf("The init_value is updated to [%d]\n", init_value);
                    //对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        //启动Updater线程，主要用于对init_value的修改，当local_value>=5
        //的时候则退出生命周期
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }

}
