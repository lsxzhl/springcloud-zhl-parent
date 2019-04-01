package com.example.demo.aop;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/25 15:44
 */
public class TestAop {

    public static void main(String[] args) {
        A a = new A();
        OneInterface oneInterface = (OneInterface) new TimeHandler().getProxyBean(a);
        oneInterface.studyAop();
    }
}
