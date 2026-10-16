package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.List;

public class SumAndAverage {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);

        System.out.println(Arrays.stream(a).count());

        int l1 = b.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of List : "+l1);

        int l2 = Arrays.stream(a).boxed().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of Array : "+l2);

        double l3 = b.stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Average of List : "+l3);

        double l4 = Arrays.stream(a).boxed().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Average of Array : "+l4);
    }
}
