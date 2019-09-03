package com.zhl.servicehi.interview.singleton;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/30 15:41
 */
public enum EnumSingleton {
    /**
     *
     */
    INSTANCE;

    private byte[] data = new byte[1024];

    private EnumSingleton(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method(){
        //调用该方法则会主动使用EnumSingleton， INSTANC将会被实例化
    }


    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}
