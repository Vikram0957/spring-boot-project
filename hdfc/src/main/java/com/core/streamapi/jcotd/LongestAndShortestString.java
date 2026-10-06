package com.core.streamapi.jcotd;

import java.util.Comparator;
import java.util.List;

public class LongestAndShortestString {
    public static void main(String[] args) {
        List<String> l = List.of("hi", "hello", "Rajeshwari", "Vikram");

        String shortest = l.stream().min(Comparator.comparingInt(String::length)).get();
        System.out.println(shortest);

        String largest = l.stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println(largest);
    }
}
