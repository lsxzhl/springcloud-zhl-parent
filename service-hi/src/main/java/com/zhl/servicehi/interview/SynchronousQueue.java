package com.zhl.servicehi.interview;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/7/29 15:08
 */
public class SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new java.util.concurrent.SynchronousQueue<>();
        System.out.print(queue.offer(1) + " ");
        System.out.print(queue.offer(2) + " ");
        System.out.print(queue.offer(3) + " ");
        System.out.print(queue.take() + " ");
        System.out.println(queue.size());
    }

}
