package com.zhl.springbootexcel.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/23 18:38
 */
public class MyLockImpl {

    private static final Logger log = LoggerFactory.getLogger(MyLockImpl.class);

    private Lock noFairLock = new ReentrantLock();
    Lock noFairLock1 = new ReentrantLock(false);
    Lock fairLock = new ReentrantLock(true);
    /**
     * 创建Condition
     */
    private Condition condition = noFairLock.newCondition();
    public void testMethod() {
        try {
            //lock加锁
            noFairLock.lock();
            //1.await方法等待
            System.out.println("开始await");
            condition.await();
            //通过创建Condition对象来使线程await，必须先执行lock方法获得锁
            //2.signal方法唤醒  condition对象的signal方法可以唤醒await线程
            condition.signal();
            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + ("" + (i + 1)));
            }
        }catch (InterruptedException e){
            log.error("有异常了：：：：", e);
            log.debug("Processing trade with id:[{}] and symbol : [{}] ", e, e);
        }finally {
            noFairLock.unlock();
        }

    }

    public static void main(String[] args) {
        new MyLockImpl().testMethod();
    }
}
