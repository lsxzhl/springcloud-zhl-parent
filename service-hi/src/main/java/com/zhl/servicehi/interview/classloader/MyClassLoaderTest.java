package com.zhl.servicehi.interview.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 13:27
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //声明一个MyClassLoader
        MyClassLoader myClassLoader = new MyClassLoader();
        //使用MyClassLoader加载HelloWorld
        Class<?> aClass = myClassLoader.loadClass("com.zhl.servicehi.interview.classloader.HelloWorld");
        System.out.println(aClass.getClassLoader());

        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = aClass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloWorld);
        System.out.println("Result:" + result);
    }

}
