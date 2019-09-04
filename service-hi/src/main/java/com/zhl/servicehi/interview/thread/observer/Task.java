package com.zhl.servicehi.interview.thread.observer;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/3 13:14
 */
@FunctionalInterface
public interface Task<T> {

    /**
     * 任务执行接口，该接口允许有返回值
     * @return
     */
    T call();
}
