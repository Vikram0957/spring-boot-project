package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicateElements {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);
        String[] c = {"Java","is","a","is", "cat", "to", "a"};
        String d = "Java is a programming language";
        List<String> e = List.of("java","is","a","is", "cat", "to", "a","ok");
        List<Character> f = List.of('a','c','a','d','a', 'c');

        Set<Integer> set = new HashSet();
        Set<Integer> dup = b.stream().filter(x -> !set.add(x)).collect(Collectors.toSet());
        System.out.println(dup);

        Set<Integer> set2 = new HashSet();
        Arrays.stream(a).boxed().filter(x -> !set2.add(x)).forEach(System.out::println);

        Set<String> set3 = new HashSet();
        e.stream().filter(x -> !set3.add(x)).forEach(System.out::println);
    }
}
