package com.zhl.lamada.threadlocaldatalose;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomThreadLocalTwo {

    //static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    /*static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    static ExecutorService pool = Executors.newFixedThreadPool(2);*/

    static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    static ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) {
       for (int i = 0;  i < 100; i++){
           int j = i;
           pool.execute( new Thread(new Runnable() {
               @Override
               public void run() {
                   CustomThreadLocalTwo.threadLocal.set("测试数据"+ j);
                   new Service().call();
               }
           }));
       }
    }

    static class Service{
        public void call(){
            CustomThreadLocalTwo.pool.execute(new Runnable() {
                @Override
                public void run() {
                    new Dao().call();
                }
            });
        }
    }

    static class Dao{
        public void call(){
            System.out.println("dao: " + CustomThreadLocalTwo.threadLocal.get());
        }
    }

}
