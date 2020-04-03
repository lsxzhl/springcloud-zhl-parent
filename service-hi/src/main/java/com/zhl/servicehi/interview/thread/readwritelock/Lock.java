package com.zhl.servicehi.interview.thread.readwritelock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 9:39
 */
public interface Lock {

    /**
     * 获取显式锁，没有获得锁的线程将被阻塞
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 释放获取的锁
     */
    void unlock();
}
