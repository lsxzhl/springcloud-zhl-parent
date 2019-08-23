package com.zhl.servicehi.interview.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/1 14:34
 */
public class ThreadConstruction {

    public static void main(String[] args) throws InterruptedException {
        // 一
        Thread thread = new Thread("TestThread");
        ThreadGroup threadGroup = new ThreadGroup("TestGroup");
        Thread thread1 = new Thread(threadGroup,"thread1");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main thread belong group:" + mainThreadGroup.getName());
        System.out.println("thread1 and main belong the same group  :" + (mainThreadGroup == thread1.getThreadGroup()));
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(555));
    }


}
