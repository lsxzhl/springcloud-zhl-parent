package com.zhl.servicehi.interview.classloader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 13:21
 */
public class HelloWorld {

    static {
        System.out.println("Hello world class is initialized.");
    }

    public String welcome(){
        return "Hello World";
    }

}
