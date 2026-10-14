package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortingOfStringAndInteger {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);
        String[] c = {"Java","is","a","is", "cat", "to", "a"};
        String d = "Java is a programming language";

        List<String> e = List.of("java","is","a","is", "cat", "to", "a","ok");
        List<Character> f = List.of('a','c','a','d','a', 'c');

        //Integer sorting
        List result = Arrays.stream(a).boxed().sorted(Comparator.naturalOrder()).toList();
        System.out.println(result);

        List result2 = Arrays.stream(a).boxed().sorted(Comparator.reverseOrder()).toList();
        System.out.println(result2);

        //String sorting
        List res = e.stream().sorted(Comparator.naturalOrder()).toList();
        System.out.println(res);

        List res2 = e.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(res2);

        //String sorting based on length
        List r1 = e.stream().sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println(r1);

        List r2 = e.stream().sorted(Comparator.comparingInt(String::length)).toList().reversed();
        System.out.println(r2);
    }
}
