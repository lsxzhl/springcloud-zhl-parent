package com.example.demo.aop;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/25 15:38
 */
public class B implements OneInterface {
    @Override
    public void studyAop() {
        System.out.println("JDK动态代理----AOP----B");
    }
}
