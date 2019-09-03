package com.zhl.servicehi.interview.classloader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 10:03
 */
public class BootStrapClassLoader {

    public static void main(String[] args) {
        System.out.println("BootStrap:" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

}
