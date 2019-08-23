package com.zhl.servicehi.interview.thread;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/1 10:47
 */
public class TicketWindow implements Runnable {

    //最多受理五wu笔业务
    private static final int MAX = 55;

    private int index = 1;

    private final static Object OBJ = new Object();

    @Override
    public void run(){
        synchronized (OBJ) {
            while (index <= MAX) {
                System.out.println("柜台：" + Thread.currentThread().getName() + "  当前号码是：" + (index++));
                try {
                    Thread.sleep(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindow ticketWindow = new TicketWindow();
        Thread t_one = new Thread(ticketWindow,"一号出号机");
        Thread t_two = new Thread(ticketWindow,"二号出号机");
        Thread t_three = new Thread(ticketWindow,"三号出号机");
        Thread t_four = new Thread(ticketWindow,"四号出号机");

        t_one.start();
        t_two.start();
        t_three.start();
        t_four.start();
    }

}
