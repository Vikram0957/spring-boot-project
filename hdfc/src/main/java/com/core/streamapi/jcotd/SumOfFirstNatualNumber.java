package com.core.streamapi.jcotd;

import java.util.stream.IntStream;

public class SumOfFirstNatualNumber {
    public static void main(String[] args) {
        int sum = IntStream.range(1, 20).sum();
        System.out.println(sum);
    }
}
