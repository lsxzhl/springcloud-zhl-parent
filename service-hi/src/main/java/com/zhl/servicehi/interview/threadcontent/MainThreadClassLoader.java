package com.zhl.servicehi.interview.threadcontent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 15:34
 */
public class MainThreadClassLoader {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String da = "2018-06-01 10:12:05";
        LocalDateTime ldt = LocalDateTime.parse(da,df);
        System.out.println("LocalDateTime:  "+ldt);
    }

}
