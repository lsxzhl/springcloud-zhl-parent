package com.zhl.servicehi.interview.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/16 13:32
 */
public interface Lock {

    /**
     * 永远阻塞，除非获得了锁。
     * @throws InterruptedException InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 增加超时功能
     * @param mills 时间
     * @throws InterruptedException InterruptedException
     * @throws TimeoutException TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 释放锁
     */
    void unlock();

    /**
     * 获得有哪些线程被阻塞
     * @return List<Thread>
     */
    List<Thread> getBlockedThreads();

}
