package com.zhl.servicehi.interview.thread.deadlock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/4 13:53
 */
public class TableEatTest {

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        TablewarePair leftTablewarePair = new TablewarePair(fork, knife);
        TablewarePair rightTablewarePair = new TablewarePair(fork, knife);
        new EatNoodleThread("A", leftTablewarePair).start();
        new EatNoodleThread("B", rightTablewarePair).start();
    }

}
