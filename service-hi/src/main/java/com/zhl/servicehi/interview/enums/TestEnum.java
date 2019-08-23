package com.zhl.servicehi.interview.enums;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/15 15:25
 */
public class TestEnum {

    @Value("${fsscServer}")
    private String fsscServer;

    public static void main(String[] args) {
       System.out.println( UseCarTypeEnum.getUseCarType("1").getTitle());
    }
}
