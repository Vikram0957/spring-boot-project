package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxOrMinNumber {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);

        Integer l1 = b.stream().max(Comparator.naturalOrder()).get();
        System.out.println("Max : "+l1);

        Integer l2 = b.stream().min(Comparator.naturalOrder()).get();
        System.out.println("Min : "+l2);

        Integer l3 =  Arrays.stream(a).boxed().max(Comparator.naturalOrder()).get();
        System.out.println("Max : "+l3);

        Integer l4 = Arrays.stream(a).boxed().min(Comparator.naturalOrder()).get();
        System.out.println("Min : "+l4);
    }
}
