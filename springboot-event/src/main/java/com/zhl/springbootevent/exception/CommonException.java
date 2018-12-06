package com.zhl.springbootevent.exception;

public class CommonException extends Exception {

    private String msg;

    private String code;

    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public Object getMsg() {
        return msg;
    }

    public Object getCode() {
        return code;
    }
}
