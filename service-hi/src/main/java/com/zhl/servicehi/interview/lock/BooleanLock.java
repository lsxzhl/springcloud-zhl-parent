package com.zhl.servicehi.interview.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/16 13:37
 */
public class BooleanLock implements Lock {

    /**
     * 当前拥有锁的线程
     */
    private Thread currentThread;

    /**
     * 锁开关
     * false：当前该锁没有被任何线程获得或者已经释放
     * true：该锁已经被某个线程获得
     */
    private boolean locked = false;

    /**
     * 存储那些线程在获取当前线程时进入了阻塞状态
     */
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        //1.使用同步代码块的方式进行方法同步
        synchronized (this) {
            //2.如果当前锁已经被某个线程获得，则该线程将加入阻塞队列，
            // 并且使当前线程wait释放对this monitor的所有权。
            while (locked) {
                /*blockedList.add(currentThread());
                this.wait();*/
                //暂存当前线程
                final Thread tempThread = currentThread();
                try{
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(tempThread);
                        this.wait();

                    }
                }catch (InterruptedException e){
                    //如果当前线程在wait时中断，则从blockedList中将其删除，避免内存泄漏
                    blockedList.remove(tempThread);
                    throw e;
                }
            }
            //3.如果当前锁没有被其他线程获得，则该线程将尝试从阻塞队列中删除自己
            blockedList.remove(currentThread());
            //4.设置开关
            this.locked = true;
            //5.记录获得锁的线程
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            //1.如果mills不合法，则默认调用lock()方法
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    //2.如果remainingMills小于等于0，则意味着当前线程被其他线程唤醒或者
                    // 在指定的wait时间到了之后还没有获得锁，则抛出超时异常。
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during" + mills);
                    }
                    if (!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                        //3.等待remainingMills的毫秒数，该值最开始是由其他线程传入的
                        // 但在多次wait的过程中会重新计算。
                        this.wait();
                        //4.重新计算remainingMills时间
                        remainingMills = endMills - System.currentTimeMillis();
                    }
                    //5.获得该锁，并且从block列表中删除当前线程，
                    // 将locked的状态修改为true并且指定获得锁的线程就是当前线程。
                    blockedList.remove(currentThread());
                    this.locked = true;
                    this.currentThread = currentThread();
                }
            }
        }
    }

    /**
     * 将locked状态修改为false，并且唤醒wait set中的其他线程，再次争抢
     * 锁资源。注意点：那个线程加的锁只能由该线程来解锁
     */
    @Override
    public void unlock() {
        synchronized (this) {
            //1.判断当前线程是否为获取锁的那个线程，只有加了锁的线程才有资格进行解锁
            if(currentThread == currentThread()){
                //2.将锁的locked状态修改为false
                this.locked = false;
                //3.
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
