package com.zhl.lamada.threadlocaldatalose;

public class CustomThreadLocal {

    //static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal.threadLocal.set("测试数据");
                new Service().call();
            }
        }).start();
    }

    static class Service{
        public void call(){
            System.out.println("service: " + Thread.currentThread().getName());
            System.out.println("service: " + CustomThreadLocal.threadLocal.get());
            //new Dao().call();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Dao().call();
                }
            }).start();
        }
    }

    static class Dao{
        public void call(){
            System.out.println("dao: " + Thread.currentThread().getName());
            System.out.println("dao: " + CustomThreadLocal.threadLocal.get());
        }
    }

}
