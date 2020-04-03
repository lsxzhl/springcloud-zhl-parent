package com.zhl.servicehi.interview.thread.guardsuspension;

import java.util.LinkedList;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/9 13:43
 */
public class GuardedSuspensionQueue {

    /**
     * 定义存放Integer类型的queue
     */
    private final LinkedList<Integer> queue = new LinkedList<>();

    /**
     * 定义queue的最大容量为100
     */
    private final int LIMIT = 100;

    /**
     * 往queue中插入数据，如果queue中的元素超过了最大容量，则会陷入阻塞
     * @param data data
     */
    public void offer(Integer data) throws InterruptedException {
        synchronized (this){
            while (queue.size() >= LIMIT){
                this.wait();
            }
            //插入元素并且唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this){
            //如果队列为空，则挂起当前线程
            while (queue.isEmpty()){
                this.wait();
            }
            //通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }


}
