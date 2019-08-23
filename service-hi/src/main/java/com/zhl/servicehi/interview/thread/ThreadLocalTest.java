package com.zhl.servicehi.interview.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/26 10:48
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024));

        threadPoolExecutor.execute(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                //threadLocal.remove();
            }

            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "===" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                threadLocal.remove();
            }
        });
        threadPoolExecutor.shutdown();

    }
//    public static void main(String[] args) {
//
//
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 100; i++) {
//                    threadLocal.set(i);
//                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } finally {
//                threadLocal.remove();
//            }
//        }, "threadLocal1").start();
//
//
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } finally {
//                threadLocal.remove();
//            }
//        }, "threadLocal2").start();
//    }

}
