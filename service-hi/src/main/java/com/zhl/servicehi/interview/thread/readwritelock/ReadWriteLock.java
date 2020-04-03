package com.zhl.servicehi.interview.thread.readwritelock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 9:42
 */
public interface ReadWriteLock {

    /**
     * 创建reader锁
     * @return lock
     */
    Lock readLock();

    /**
     * 创建write锁
     * @return lock
     */
    Lock writeLock();

    /**
     * 获取当前有多少线程正在执行写操作
     * @return int
     */
    int getWritingWriters();

    /**
     * 获取当前有多少线程正在等待获取写入锁
     * @return
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少线程正在等待获取reader锁
     * @return int
     */
    int getReadingReaders();

    /**
     * 工厂方法，创建ReadWriteLock
     * @return
     */
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }

}
