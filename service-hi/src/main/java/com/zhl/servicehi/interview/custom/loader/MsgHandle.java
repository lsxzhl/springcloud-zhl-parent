package com.zhl.servicehi.interview.custom.loader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/6 11:03
 */
public class MsgHandle implements Runnable {
    @Override
    public void run() {
        while (true){
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.logic();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
