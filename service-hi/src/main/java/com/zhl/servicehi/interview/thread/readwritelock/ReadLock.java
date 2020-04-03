package com.zhl.servicehi.interview.thread.readwritelock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 11:33
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock){
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        //使用Mutex作为锁
        synchronized (readWriteLock.getMutex()){
            //若此时有线程在进行写操作，或者有写线程在等待并且偏向写锁的
            //标识为true时，就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.getPreferWrite() && readWriteLock.getWaitingWriters() > 0)){
                readWriteLock.getMutex().wait();
            }
            //成功获得读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        //使用Mutex作为锁，并且进行同步
        synchronized (readWriteLock.getMutex()){
            //释放锁的过程就是使得当前reading的数量减一
            readWriteLock.decrementReadingReaders();
            //将perferWriter设置为true，可以使得writer线程获得更多的机会
            readWriteLock.changePrefer(true);
            //通知唤醒uiMutex关联monitor waitset中的线程
            readWriteLock.getMutex().notifyAll();
        }
    }
}
