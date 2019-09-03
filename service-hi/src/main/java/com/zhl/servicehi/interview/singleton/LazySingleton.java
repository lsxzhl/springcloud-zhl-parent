package com.zhl.servicehi.interview.singleton;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/29 14:50
 */
public class LazySingleton {

    private byte[] data = new byte[1024];

    private static LazySingleton lazySingleton = null;

    private LazySingleton(){

    }

    /**
     * 多线程下会导致lazySingleton被实例化一次以上
     * @return lazySingleton
     */
    public static LazySingleton getInstance(){
        if (null == lazySingleton){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
