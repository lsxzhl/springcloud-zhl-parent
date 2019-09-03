package com.zhl.servicehi.interview.volatile_example;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/29 10:19
 */
public class VolatileTest {

    private static volatile int i = 0;

    private static final CountDownLatch latch = new CountDownLatch(10);

    private static void inc(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            new Thread(() ->{
                for (int k = 0; k < 1000; k++) {
                    inc();
                }
                //使用计数器减1
                latch.countDown();
            }).start();
        }
        //等待所有的线程完成工作
        latch.await();
        System.out.println(i);
    }

}
