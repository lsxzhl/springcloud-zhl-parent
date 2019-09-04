package com.zhl.servicehi.interview.thread.observer;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/3 11:43
 */
public interface TaskLifecycle<T> {

    /**
     * 任务启动时会触发onStart方法
     * @param thread Thread
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning方法
     * @param thread Thread
     */
    void onRunning(Thread thread);

    /**
     * 任务运行结束时会触发onFinish方法，其中result是任务执行结束后的结果
     * @param thread thread
     * @param result result
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务运行报错时会触发onError方法
     * @param thread thread
     * @param e Exception
     */
    void onError(Thread thread, Exception e);

    class EmptyLifecycle<T> implements TaskLifecycle<T>{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }

}
