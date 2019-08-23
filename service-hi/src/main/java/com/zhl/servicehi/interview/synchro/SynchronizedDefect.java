package com.zhl.servicehi.interview.synchro;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/16 13:19
 */
public class SynchronizedDefect {

    public synchronized  void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //阻塞时长无法控制 thread2若因争抢某个monitor的锁而进入阻塞状态
        //那么它是无法中断的
        //synchronized无法像sleep wait方法来捕获得到中断信号

        SynchronizedDefect defect = new SynchronizedDefect();
        Thread thread1 = new Thread(defect::syncMethod, "T1");
        //make sure the thread started
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread thread2 = new Thread(defect::syncMethod, "T1");
        thread2.start();

    }
}
