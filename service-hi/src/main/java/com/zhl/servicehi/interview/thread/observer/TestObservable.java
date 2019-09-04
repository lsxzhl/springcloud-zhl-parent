package com.zhl.servicehi.interview.thread.observer;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/3 14:18
 */
public class TestObservable {

    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() ->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return null;
        });
        observableThread.start();
    }

}
