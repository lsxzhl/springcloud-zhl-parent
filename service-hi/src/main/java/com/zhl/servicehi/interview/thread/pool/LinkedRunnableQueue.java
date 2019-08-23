package com.zhl.servicehi.interview.thread.pool;

import java.util.LinkedList;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 11:54
 */
public class LinkedRunnableQueue implements RunnableQueue {
    /**
     * 任务队列的最大容量，在构造时传入
     */
    private final int limit;

    /**
     * 若任务队列中的任务已经满了，则需要执行拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList) {
             if(runnableLinkedList.size() >= limit){
                 //无法容纳新的任务时执行拒绝策略
                 denyPolicy.reject(runnable, threadPool);
             }else {
                 //将任务加入至队尾，并且唤醒阻塞中的线程
                 runnableLinkedList.addLast(runnable);
                 runnableLinkedList.notifyAll();
             }
        }
    }

    @Override
    public Runnable take() {
        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
                try {
                    //如果任务队列中没有可执行任务，则当前线程将会挂起
                    //进入runnableLinkedList关联的monitor waitset中等待
                    //唤醒（新的任务加入）
                    runnableLinkedList.wait();
                } catch (InterruptedException e) {
                    //被中断时需要将该异常抛出
                    e.printStackTrace();
                }
            }
        }
        //从任务队列头部移除一个任务
        return runnableLinkedList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList){
            //返回当前任务队列中的任务数
            return runnableLinkedList.size();
        }
    }
}
