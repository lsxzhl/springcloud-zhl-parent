package com.zhl.servicehi.interview.thread.readwritelock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 11:34
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMutex()) {
            try {
                //首先使等待获取写入锁的数字加一
                readWriteLock.incrementWaitingWriters();
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMutex().wait();
                }
            } finally {
                //成功获取到了写入锁，使得等待获取写入锁的计数器减一
                this.readWriteLock.decrementWaitingWriters();
            }
            //将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMutex()) {
            //减少正在写入锁的线程计数器
           readWriteLock.decrementWritingWriters();
           //将偏好状态修改为false，可以使得读锁最快速得获得
           readWriteLock.changePrefer(false);
           //通知唤醒其他在Mutex moniter waitset中得线程
           readWriteLock.getMutex().notifyAll();
        }
    }
}
