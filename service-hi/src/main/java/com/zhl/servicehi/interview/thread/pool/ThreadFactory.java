package com.zhl.servicehi.interview.thread.pool;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 10:41
 * 创建线程的工厂
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
