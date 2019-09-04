package com.zhl.servicehi.interview.thread.deadlock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/4 13:58
 */
public class TablewarePair {

    private final Tableware leftTool;

    private final Tableware rightTool;

    public TablewarePair(Tableware leftTool, Tableware rightTool){
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public Tableware getLeftTool(){
        return leftTool;
    }

    public Tableware getRightTool(){
        return rightTool;
    }

}
