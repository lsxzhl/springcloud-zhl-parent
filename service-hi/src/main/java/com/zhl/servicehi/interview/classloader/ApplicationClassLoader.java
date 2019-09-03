package com.zhl.servicehi.interview.classloader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 10:12
 */
public class ApplicationClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }

}
