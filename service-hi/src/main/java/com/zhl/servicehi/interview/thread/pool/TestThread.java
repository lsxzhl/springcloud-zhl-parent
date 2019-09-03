package com.zhl.servicehi.interview.thread.pool;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/28 17:34
 */
public class TestThread extends Person{

    public static void main(String[] args) {
        /*Thread thread = new Thread(){
            @Override
            public void run(){
                pong();
            }
        };
        thread.start();
        System.out.print("ping");*/
        /*new Person();
        new TestThread();*/

        new Child("mike");
        System.out.println(Math.round(-99.5));

    }

    static void pong(){
        System.out.print("pong");
    }

}

class Person{

    String name;

    public Person(){
        System.out.print("1");
    }

    public Person(String name){
        System.out.print("2");
        this.name = name;
    }
}

class Child extends Person{
    Person father;
    public Child(String name){
        super(name);
        System.out.print("3");
        father = new Person(name + ":F");
    }
    public Child(){
        System.out.print("4");
    }
}