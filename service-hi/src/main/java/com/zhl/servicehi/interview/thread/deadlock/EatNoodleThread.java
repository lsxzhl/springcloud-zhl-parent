package com.zhl.servicehi.interview.thread.deadlock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/4 11:22
 */
public class EatNoodleThread extends Thread {

    private final String name;

    /*private final Tableware leftTool;

    private final Tableware rightTool;*/

    private final TablewarePair tablewarePair;

    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left+)");
        }
    }

}
