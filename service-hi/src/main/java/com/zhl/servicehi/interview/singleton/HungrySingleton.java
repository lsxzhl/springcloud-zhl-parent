package com.zhl.servicehi.interview.singleton;

/**
 * 饿汉式
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/29 14:29
 * final修饰类 不允许被继承
 */
public final class HungrySingleton {

    //实例变量
    private byte[] data = new byte[1024];

    //在定义实例对象的时候直接初始化
    private static HungrySingleton singleton = new HungrySingleton();

    //私有构造函数，不允许外部new
    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return singleton;
    }

}
