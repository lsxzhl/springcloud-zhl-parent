package com.zhl.servicehi.interview.singleton;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/30 15:27
 */
public class HolderSingleton {

    private byte[] data = new byte[1024];

    private HolderSingleton(){

    }

    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }

    /**
     * 调用getInsatance方法，事实上是获得Holder的instance静态舒总
     * @return
     */
    public static HolderSingleton getInstance(){
        return Holder.instance;
    }

}
