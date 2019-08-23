package com.zhl.servicehi.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/20 15:43
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        //设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2.这里会出现unchecked异常
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }

}
