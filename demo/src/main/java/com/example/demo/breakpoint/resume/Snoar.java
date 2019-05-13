package com.example.demo.breakpoint.resume;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/26 13:14
 */
public class Snoar {

    public static void main(String[] args) {
        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        AtomicInteger atomicInteger2 = new AtomicInteger(0);
        if(atomicInteger1.equals(atomicInteger2)){
            System.out.println("xiangdeng");
        }else {
            System.out.println("No");
        }
    }
}
