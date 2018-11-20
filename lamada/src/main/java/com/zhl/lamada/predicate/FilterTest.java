package com.zhl.lamada.predicate;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;

public class FilterTest {

    public static void main(String[] args) {

        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);

        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println(intSummaryStatistics.getAverage());

        System.out.println(intSummaryStatistics.getCount());

        System.out.println(intSummaryStatistics.getMax());

        System.out.println(intSummaryStatistics.getMin());

        System.out.println(intSummaryStatistics.getSum());


    }

}
