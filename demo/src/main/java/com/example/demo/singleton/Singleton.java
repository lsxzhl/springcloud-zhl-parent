package com.example.demo.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/18 9:10
 */
public class Singleton {

    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<Singleton>();

    private Singleton(){}

    public static Singleton getInstance(){
        for (;;){
            Singleton singleton = INSTANCE.get();
            if(null != singleton){
                return singleton;
            }
            singleton = new Singleton();
            if(INSTANCE.compareAndSet(null,singleton)){
                return singleton;
            }
        }
    }

}
