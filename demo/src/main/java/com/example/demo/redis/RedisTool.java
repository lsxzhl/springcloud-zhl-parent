package com.example.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/11 9:36
 */
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    键
     * @param requestId  值
     * @param expireTime 过期时间
     * @return 是否成功获取
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param jedis  redis客户端
     * @param lockKey   键
     * @param requestId  值
     * @return  是否成功释放
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if(RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    public static void errorGetLock1(Jedis jedis, String lockKey, String requestId, int expireTime) {
        Long result = jedis.setnx(lockKey, requestId);
        if (result == 1) {
            //error reason :若在这里程序突然崩溃，则无法设置过期时间，将发生死锁
            jedis.expire(lockKey, expireTime);
        }
    }

    public static boolean errorGetLock2(Jedis jedis, String lockKey, int expireTime) {
        long expires = System.currentTimeMillis() + expireTime;
        String expireStr = String.valueOf(expires);
        //如果当前锁不存在，返回加锁成功
        if (jedis.setnx(lockKey, expireStr) == 1) {
            return true;
        }

        //如果锁存在，获取锁的过期时间
        String currentValueStr = jedis.get(lockKey);
        if(currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()){
            //锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
            String oldValueStr = jedis.getSet(lockKey, expireStr);
            if(oldValueStr != null && oldValueStr.equals(currentValueStr)){
                //考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权加锁
                return true;
            }
        }
        return false;
    }

}
