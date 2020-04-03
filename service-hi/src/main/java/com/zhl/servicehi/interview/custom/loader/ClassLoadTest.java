package com.zhl.servicehi.interview.custom.loader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/6 11:06
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        new Thread(new MsgHandle()).start();
    }

}
