package com.example.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/25 15:39
 */
public class TimeHandler implements InvocationHandler {

    private OneInterface oneInterface;

    public Object getProxyBean(OneInterface oneInterface){
        this.oneInterface  = oneInterface;
        Class clazz = oneInterface.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        oneInterface.studyAop();
        long end = System.currentTimeMillis();
        System.out.println("++++++++time::"+(end-start));
        return null;
    }
}
