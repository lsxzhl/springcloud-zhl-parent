package com.zhl.servicehi.interview.custom.loader;

import java.time.LocalTime;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/6 10:26
 */
public class CustomManager implements BaseManager {

    @Override
    public void logic() {
        System.out.println(LocalTime.now() + ": Java类的热加载");
    }
}
