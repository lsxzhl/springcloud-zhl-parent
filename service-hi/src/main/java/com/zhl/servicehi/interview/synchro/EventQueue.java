package com.zhl.servicehi.interview.synchro;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/12 17:42
 */
public class EventQueue {

    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    /**
     * 提交一个Event至队尾
     *
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    System.out.println("The Queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "The new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    /**
     * 从对头获取Event
     *
     * @return
     */
    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + "the queue is empty ");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            System.out.println(Thread.currentThread().getName() + " the event " + event + " is handled ");
            return event;
        }
    }

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            for (; ; ){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }

}
