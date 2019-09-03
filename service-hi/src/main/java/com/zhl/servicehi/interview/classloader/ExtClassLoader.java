package com.zhl.servicehi.interview.classloader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/26 10:08
 */
public class ExtClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        System.out.print(ids);
    }

}
