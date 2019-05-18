package com.example.demo.reference;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/13 10:04
 */
public class ReferenceDemo {

    public Object instance = null;

    private static final int _Mb = 1024 * 1024;
    /**
     * 申请内存
     */
    private byte[] bigSize = new byte[10 * _Mb];

    public static void main(String[] args) {

        System.out.println(String.format("开始：%d M", Runtime.getRuntime().freeMemory() / (1024 * 1024)));
        ReferenceDemo referenceDemo = new ReferenceDemo();
        ReferenceDemo referenceDemo1 = new ReferenceDemo();
        referenceDemo.instance = referenceDemo1;
        referenceDemo1.instance = referenceDemo;
        System.out.println(String.format("运行: %d M", Runtime.getRuntime().freeMemory() / (1024 * 1024)));
        //手动触发垃圾回收
        System.gc();
        System.out.println(String.format("结束: %d M", Runtime.getRuntime().freeMemory() / (1024 * 1024)));

    }



}
