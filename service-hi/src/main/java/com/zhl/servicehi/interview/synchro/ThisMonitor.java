package com.zhl.servicehi.interview.synchro;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/8 17:01
 * 两个方法都被synchronized关键字修饰，启动两个线程分别访问这两个方法
 * question1：synchronized关键字修饰了同一个实例对象的两个不同的方法
 *            那么与之对应的monitor是什么？？？
 * question2：两个monitor是否一致呢？？？
 */
public class ThisMonitor {

    public synchronized void method_one(){
        System.out.println(Thread.currentThread().getName() + " enter to method_one!!!");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method_two(){
        System.out.println(Thread.currentThread().getName() + " enter to method_two!!!");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method_one,"T1").start();
        new Thread(thisMonitor::method_two,"T2").start();
    }

}
