package com.example.demo.thread;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/3 11:10
 */
public class ThreadTest {

    private static final long count = 10000L;

    public static void main(String[] args) throws InterruptedException {

        //并发计算
        concurrency();
        //单线程计算
        serial();

    }


    private static void concurrency() throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
                System.out.println(a);
            }
        });

        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + time + "ms,b=" + b);

    }


    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;

        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);

    }


}
