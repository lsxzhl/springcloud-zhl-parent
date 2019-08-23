package com.zhl.servicehi.interview.thread.pool;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 10:36
 * 任务队列，主要用于缓存提交到线程池中的任务
 */
public interface RunnableQueue {

    /**
     * 当有新的任务进来时首先会offer到队列中
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取runable
     * @return
     */
    Runnable take();

    /**
     * 获取任务队列中任务的数量
     * @return
     */
    int size();

}
