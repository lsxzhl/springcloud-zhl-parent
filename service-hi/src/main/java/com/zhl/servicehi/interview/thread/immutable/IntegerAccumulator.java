package com.zhl.servicehi.interview.thread.immutable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/5 15:33
 */
public class IntegerAccumulator {

    private int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    /**
     * 对初始值增加i
     *
     * @param i
     * @return
     */
    public int add(int i) {
        this.init += i;
        return this.init;
    }

    /**
     * 返回当前的初始值
     *
     * @return
     */
    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        //定义累加器，并且将设置初始值为0
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        //定义三个线程，并且分别启动
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue;
                int result;
                synchronized (IntegerAccumulator.class) {
                    //首先获得old value
                    oldValue = accumulator.getValue();
                    //然后调用add方法计算
                    result = accumulator.add(inc);
                }
                System.out.println(oldValue + "+" + inc + "=" + result);
                if (inc + oldValue != result) {
                    System.err.println("ERROR:" + oldValue + "+" + inc + "=" + result);
                }
                inc++;
                //模拟延迟
                slowly();
            }
        }).start());
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
