package com.zhl.servicehi.interview.lock;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/16 16:01
 */
public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    /**
     * 使用try...finally语句块确保lock每次都能被正确释放
     */
    public void syncMethod() {
        //加锁
        try {
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        BooleanLockTest booleanLockTest = new BooleanLockTest();
//        //定义一个线程并启动
//        IntStream.range(0,10)
//                .mapToObj(i -> new Thread(booleanLockTest::syncMethod))
//                .forEach(Thread::start);

        BooleanLockTest booleanLockTest = new BooleanLockTest();
        new Thread(booleanLockTest::syncMethod,"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread thread = new Thread(booleanLockTest::syncMethod,"T2");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();


    }

}
