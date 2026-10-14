package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeparateEvenAndOdd {

    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);
        Map<Boolean, List<Integer>> part = b.stream().collect(Collectors.partitioningBy(x -> x%2==0));
        System.out.println(part);

        Map<Boolean, List<Integer>> part2 = Arrays.stream(a).boxed().collect(Collectors.partitioningBy(x -> x%2==0));
        System.out.println(part2);
    }
}
