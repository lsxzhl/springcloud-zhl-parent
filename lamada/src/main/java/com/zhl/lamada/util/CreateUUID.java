package com.zhl.lamada.util;

import java.util.UUID;

public class CreateUUID {

    public static void main(String[] args) {
        /*for(int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }*/


                int n = 8;
                System.out.println(n>6?1:2.0);//1.0  规则3
                System.out.println(n>6?1:2);//1 规则1
                System.out.println(n>6?'a':2L);//97L 规则3
                System.out.println(n>6?'a':2);//a  规则2
                System.out.println(n>6?'a':Character.MAX_VALUE);//a 规则2
                System.out.println(n = (n>6?'a':Character.MAX_VALUE+1));//97 规则3


    }

}
