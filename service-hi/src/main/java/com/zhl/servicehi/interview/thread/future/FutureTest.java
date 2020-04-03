package com.zhl.servicehi.interview.thread.future;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/9 13:08
 */
public class FutureTest {

    /**
     * 定义不需要返回值的FutureService
     */
    FutureService<Void, Void> service = FutureService.newService();

    Future<?> future = service.submit(() ->{
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am finish done.");
    });


    public static void main(String[] args) {

    }

}
