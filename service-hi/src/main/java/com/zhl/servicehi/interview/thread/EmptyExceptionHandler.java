package com.zhl.servicehi.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/20 15:55
 */
public class EmptyExceptionHandler {

    public static void main(String[] args) {
        // get current thread's thread group
        ThreadGroup mianGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mianGroup.getName());
        System.out.println(mianGroup.getParent());
        System.out.println(mianGroup.getParent().getParent());

        final Thread thread = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            //here will throw unchecked exception
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }

}
