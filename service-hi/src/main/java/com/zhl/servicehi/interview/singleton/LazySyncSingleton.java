package com.zhl.servicehi.interview.singleton;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/29 15:05
 */
public final class LazySyncSingleton {

    private byte[] data = new byte[1024];

    private static LazySyncSingleton instance = null;

    private LazySyncSingleton(){

    }

    /**
     * 同一时刻只能被一个线程所访问， 性能低下
     * @return
     */
    public static synchronized LazySyncSingleton getInstance(){
        if(null == instance){
            instance = new LazySyncSingleton();
        }
        return instance;
    }

}
