package com.example.demo.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/1 18:00
 */
public class MyHandler implements InvocationHandler {

    private Man man;

    public MyHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(man,null);
        after();
        return null;
    }

    public void before(){
        System.out.println("我将会努力做好自己！！！！");
    }

    public void after(){
        System.out.println("我会好好珍惜你，让你幸福！！！");
    }


}
