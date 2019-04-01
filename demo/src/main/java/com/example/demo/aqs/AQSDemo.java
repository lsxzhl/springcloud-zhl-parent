package com.example.demo.aqs;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/14 11:08
 */
public class AQSDemo {

    private static final Unsafe unsafe = getUnsafe();
    private static final long stateOffset = 0;
    private static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        try {
            unsafe.objectFieldOffset(AQSDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private volatile  int state;

    private List<Thread> threads = new ArrayList<>();

    public void lock(){
        if(!unsafe.compareAndSwapInt(state,stateOffset,0,1)){
            threads.add(Thread.currentThread());
            LockSupport.park();
            Thread.interrupted();
        }
    }

    public void unlock(){
        state = 0;
        if(!threads.isEmpty()){
            Thread first = threads.remove(0);
            LockSupport.unpark(first);
        }
    }

    static class MyThread extends Thread{

        private AQSDemo lock;

        public MyThread(AQSDemo lock){
            this.lock = lock;
        }

        @Override
        public void run(){

            try {
                lock.lock();
                System.out.println("run");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        AQSDemo lock = new AQSDemo();
        MyThread a1 = new MyThread(lock);
        MyThread a2 = new MyThread(lock);
        MyThread a3 = new MyThread(lock);
        a1.start();
        a2.start();
        a3.start();
    }



}
