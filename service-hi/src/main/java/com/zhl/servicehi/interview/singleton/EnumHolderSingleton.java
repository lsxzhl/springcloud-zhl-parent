package com.zhl.servicehi.interview.singleton;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/30 15:48
 */
public class EnumHolderSingleton {

    private byte[] data = new byte[1024];

    private EnumHolderSingleton(){

    }

    /**
     * 使用枚举充当holder
     */
    private enum EnumHolder{
        /**
         *
         */
        INSTANCE;
        private EnumHolderSingleton instance;
        EnumHolder(){
            this.instance = new EnumHolderSingleton();
        }
        private EnumHolderSingleton getInstance(){
            return instance;
        }
    }

    public static EnumHolderSingleton getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }

}
