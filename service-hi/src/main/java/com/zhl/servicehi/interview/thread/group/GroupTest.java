package com.zhl.servicehi.interview.thread.group;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2020/2/28 15:40
 */
public class GroupTest {

    public static void main(String[] args) {
        ThreadGroup topThreadGroup = Thread.currentThread().getThreadGroup();

        while (topThreadGroup.getParent() != null) {
            topThreadGroup = topThreadGroup.getParent();
        }
        Thread[] threads = new Thread[topThreadGroup.activeCount()];

        int nr = topThreadGroup.enumerate(threads);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nr; i++) {
            builder.append(threads[i].getName()).append("\nState: ").
                    append(threads[i].getState()).append("\n");
            String stackTrace = StringUtils.join(threads[i].getStackTrace(), "\n");
            builder.append(stackTrace);
        }
        System.out.println("=======" +builder);
    }

}
