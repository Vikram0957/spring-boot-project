package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.List;

public class FilterNumber {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);

        List<Integer> l1 = b.stream().filter(x -> x%2==0).toList();
        System.out.println("Multiple of 2 : "+l1);

        List<Integer> l2 = Arrays.stream(a).boxed().filter(x -> x%5==0).toList();
        System.out.println("Multiple of 5 : "+l2);
    }
}
