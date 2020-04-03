package com.zhl.servicehi.interview.thread.future;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/6 10:51
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    /**
     * 给定一个参数，经过计算返回结果
     * @param input input
     * @return out
     */
    OUT get(IN input);

}
