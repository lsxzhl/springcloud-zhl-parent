package com.zhl.servicehi.interview.singleton;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/30 11:44
 */
public class DoubleCheckSingleton {

    private byte[] data = new byte[1024];

    private volatile static DoubleCheckSingleton instance = null;

    Connection connection;

    Socket socket;

    private DoubleCheckSingleton(){

    }

    public static DoubleCheckSingleton getInstance(){
        //当instance为null时，进入同步代码块，同时
        //该判断避免了每次都需要进入同步代码块，提高效率
        if (null == instance){
            //只有一个线程能够获得DoubleCheckSingleton.class
            //关联的monitor
            synchronized (DoubleCheckSingleton.class){
                if(null == instance){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }


}
