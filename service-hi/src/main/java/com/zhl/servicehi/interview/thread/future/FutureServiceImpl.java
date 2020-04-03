package com.zhl.servicehi.interview.thread.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 当提交任务时创建一个新的线程来受理该业务，
 * 进而达到任务异步执行的效果
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/6 10:53
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    /**
     * 为执行的线程指定名字前缀
     */
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName(){
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() ->{
            runnable.run();
            //任务执行结束后将null作为结果传给future
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future submit(Task task, Object input) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() ->{
            OUT result = (OUT) task.get(input);
            //任务结束后将真实的结果通过finish方法传递给future
            futureTask.finish(result);
        }).start();
        return futureTask;
    }
}
