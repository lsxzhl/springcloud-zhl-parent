package com.zhl.servicehi.interview.fly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/2 16:30
 */
public class FlightQueryExample {

    //1.合作的各大航空公司
    private static List<String> flightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        System.out.println("==========result============");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {

        final List<String> result = new ArrayList<>();

        //2.创建查询航班信息的线程列表
        List<FightQueryTask> tasks = flightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(toList());
        //3.分别启动这几个线程
        tasks.forEach(Thread::start);
        //4.分别调用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //5.在此之前，当前线程会阻塞住，获取每一个查询线程的结果，并且加入到result中
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);
    }


}
