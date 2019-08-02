package com.zhl.servicehi.service.impl;

import com.zhl.servicehi.service.TestAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/29 16:01
 */
@Service
public class TestAsyncServiceImpl implements TestAsyncService {

    /**
     * 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
     */
    @Async
    public void function1() {
        System.out.println("f1 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void function2() {
        System.out.println("f2 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
