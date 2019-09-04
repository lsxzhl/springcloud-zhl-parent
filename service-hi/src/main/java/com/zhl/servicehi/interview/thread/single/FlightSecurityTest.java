package com.zhl.servicehi.interview.thread.single;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/4 10:43
 */
public class FlightSecurityTest {

    static class Passengers extends Thread {

        private final FlightSecurity flightSecurity;
        private final String idCard;
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                flightSecurity.pass(boardingPass,idCard);
            }
        }

    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456", "AF12256").start();
        new Passengers(flightSecurity, "B123456", "BF12256").start();
        new Passengers(flightSecurity, "C123456", "CF12256").start();
    }


}
