package com.zhl.lamada.list;

import java.util.Arrays;
import java.util.List;

public class ListLamada {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for(String fea : list){
            System.out.println(fea);
        }

        list.forEach((n) -> System.out.println(n));


        //方法引用
        list.forEach(System.out :: println);

    }

}
