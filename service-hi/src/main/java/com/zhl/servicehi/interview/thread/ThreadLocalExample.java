package com.zhl.servicehi.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/9 16:20
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        //创建ThreadLocal实例
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        //创建十个线程，使用threadLocal
        IntStream.range(0,10).forEach(i -> new Thread(() ->{
            try {
                threadLocal.set(i);
                System.out.println(Thread.currentThread() + " set i " + threadLocal.get());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread() + " get i " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

    }

}
