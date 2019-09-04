package com.zhl.servicehi.interview.thread.deadlock;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/4 11:19
 */
public class Tableware {

    /**
     * 餐具名称
     */
    private final String toolName;

    public Tableware(String toolName){
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tableware{" +
                "toolName='" + toolName + '\'' +
                '}';
    }
}
