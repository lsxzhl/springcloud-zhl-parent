package com.zhl.servicehi.interview.synchro;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/16 14:37
 */
public class TestCountDownLatch {

    private CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) {
      //  new TestCountDownLatch().begin();
        System.out.println("121.1345_32.2729".replace("_",","));
    }

    private class Runner implements Runnable{

        private int result;

        public Runner(int result){
            this.result = result;
        }

        @Override
        public void run() {
            try {
                //模拟跑了多少秒
                Thread.sleep(result=1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //跑完了计数器就减1
                countDownLatch.countDown();
            }
        }
    }

    private void begin(){
        System.out.println("赛跑开始");
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 4; i++) {
            //随机设置每个运动员跑多少秒结束
            int result = random.nextInt(3)+1;
            new Thread(new Runner(result)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有人都跑完了，裁判开始计算成绩");
    }

}
