package com.zhl.servicehi.interview.thread.future;


/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/6 10:34
 */
public interface FutureService<IN, OUT> {

    /**
     * 提交不需要返回值的任务，Future.get方法返回的将会是null
     * @param runnable runnable
     * @return future
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交需要返回值的任务，其中Task接口代替了Runnable接口
     * @param task task
     * @param input input
     * @return future
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    static <T, R> FutureService<T, R> newService(){
        return new FutureServiceImpl<>();
    }


}
