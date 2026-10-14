package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.List;

public class DistinctElement {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);

        List<Integer> result = b.stream().distinct().toList();
        System.out.println(result);

        List<Integer> res = Arrays.stream(a).boxed().distinct().toList();
        System.out.println(res);
    }
}
