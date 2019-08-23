package com.zhl.servicehi.interview.thread.pool;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 15:56
 */
public class DefaultThreadFactory implements ThreadFactory{
    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread();
    }
}
