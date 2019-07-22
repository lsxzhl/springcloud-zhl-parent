package com.zhl.springbootexcel.excel;

import java.util.concurrent.Semaphore;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/24 9:49
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //创建一个计数阈值为5的信号量对象
        //只能5个线程同时访问
        Semaphore semaphore = new Semaphore(5);
        try {
            //申请许可  默认为可响应中断锁
            semaphore.acquire();
            try {
                //业务逻辑
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //释放许可
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
