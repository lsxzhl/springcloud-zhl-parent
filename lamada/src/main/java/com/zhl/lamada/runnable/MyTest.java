package com.zhl.lamada.runnable;

public class MyTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                 System.out.println("java 8  之前的写法");
            }
        }).start();

        new Thread(() -> {System.out.println("java 8 之后的写法");}).start();

    }


}
