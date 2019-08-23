package com.zhl.servicehi.interview.synchro;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/8 16:55
 */
public class DeadLock {

    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();

    public void read(){
        synchronized (MUTEX_READ){
            synchronized (MUTEX_WRITE){
                System.out.println("I'm test dead lock!!!");
            }
        }
    }

    public void write(){
        synchronized (MUTEX_WRITE){
            synchronized (MUTEX_READ){
                System.out.println("我在测试死锁！");
            }
        }
    }

    public static void main(String[] args) {

        final DeadLock deadLock = new DeadLock();
        new Thread(()->{
            while (true){
                deadLock.read();
            }
        }, "RWAD_THREAD").start();

        new Thread(()->{
            while (true){
                deadLock.write();
            }
        },"WRITE_THREAD    ").start();

    }

}
