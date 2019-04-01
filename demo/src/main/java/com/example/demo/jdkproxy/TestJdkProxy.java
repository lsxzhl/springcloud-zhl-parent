package com.example.demo.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/1 18:03
 */
public class TestJdkProxy {

    public static void main(String[] args) {
        Man man = new ZhangHaiLong();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) Proxy.newProxyInstance(man.getClass().getClassLoader(),
                new Class[]{Man.class},myHandler);
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findMyGirlFriend();
        //打印JVM在内存中生成的动态代理类
        createProxyClassFile(Man.class);
    }
    
    private static void createProxyClassFile(Class c){
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy{}", new Class[]{c});
        //写入文件中
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy{}.class");
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
