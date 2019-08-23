package com.zhl.servicehi.interview.thread.pool.exception;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/22 10:55
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }

}
