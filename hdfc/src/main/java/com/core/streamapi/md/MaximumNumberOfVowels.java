package com.core.streamapi.md;

import java.util.*;
import java.util.stream.*;

public class MaximumNumberOfVowels {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");

        Map<String, Long> map = strings.stream().collect(Collectors.toMap(x -> x, x -> x.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count()));

        long max = map.values().stream().mapToLong(Long::longValue).max().orElse(0);

        var list = map.entrySet().stream().filter(x -> x.getValue() == max).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(map);
        System.out.println(max);
        System.out.println(list);
    }
}