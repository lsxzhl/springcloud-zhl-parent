package com.zhl.servicehi.interview.thread.pool;

import com.zhl.servicehi.interview.thread.pool.exception.RunnableDenyException;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 10:44
 * 当Queue中的runnable达到了limit上限时，决定采用何种
 * 策略通知提交者
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool) throws RunnableDenyException;

    /**
     * 该拒绝策略会直接将任务丢弃
     */
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    /**
     * 该拒绝策略会向任务提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) throws RunnableDenyException {
            throw new RunnableDenyException("The runnable " + runnable + " will be abort ");
        }
    }

    /**
     * 该拒绝策略会使任务在提交者所在的线程中执行任务
     */
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) throws RunnableDenyException {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }


}
