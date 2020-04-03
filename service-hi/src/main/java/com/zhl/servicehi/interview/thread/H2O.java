package com.zhl.servicehi.interview.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/18 13:30
 */
public class H2O {

    private static final Logger log = LoggerFactory.getLogger(H2O.class);

    private CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            semaphoreH.release(2);
            semaphoreO.release();
        }
    });

    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    public H2O() {
        this.semaphoreH = new Semaphore(2);
        this.semaphoreO = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        releaseHydrogen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            log.error(e.getMessage());
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        releaseOxygen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            log.error(e.getMessage());
        }
    }

}
