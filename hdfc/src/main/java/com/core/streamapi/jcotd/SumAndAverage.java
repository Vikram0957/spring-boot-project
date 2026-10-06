package com.core.streamapi.jcotd;

import java.util.List;

public class SumAndAverage {
    public static void main(String[] args) {
        List<Integer> list = List.of(34,65,23,12,89,56,41);
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        double average = list.stream().mapToDouble(Integer::intValue).average().getAsDouble();
        System.out.println(average);
    }
}
