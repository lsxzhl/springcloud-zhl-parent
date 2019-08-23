package com.zhl.servicehi.interview.thread;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/20 15:16
 */
public class ThreadGroupCreator {

    public static void main(String[] args) {
        //1.获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //2.定义一个新的group
        ThreadGroup group = new ThreadGroup("Group Zhl");
        //3.比较
        System.out.println(group.getParent() == currentGroup);
        //4.定义group1， 指定group为其父group
        ThreadGroup group1 = new ThreadGroup(group, "Group Lsx");
        System.out.println(group1.getParent() == group);
    }
}
