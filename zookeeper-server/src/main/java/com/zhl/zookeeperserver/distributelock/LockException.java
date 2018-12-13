package com.zhl.zookeeperserver.distributelock;

public class LockException extends RuntimeException {
    public LockException(String e) {
        super(e);
    }
    public LockException(Exception e) {
        super(e);
    }
}
