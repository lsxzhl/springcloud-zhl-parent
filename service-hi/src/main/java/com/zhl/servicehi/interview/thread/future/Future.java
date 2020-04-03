package com.zhl.servicehi.interview.thread.future;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/6 10:13
 */
public interface Future<T> {

    /**
     * 返回计算后的结果，该方法会陷入阻塞状态
     * @return t
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * 判断任务是否已经被执行完成
     * @return boolean
     */
    boolean done();

}
