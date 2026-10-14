package com.core.streamapi.jcotd;

import java.util.List;
import java.util.stream.Collectors;

public class AddPrefixAndSuffix {
    public static void main(String[] args) {
        List<String> e = List.of("java","is","a","is", "cat", "to", "a","ok");

        String l1 = e.stream().collect(Collectors.joining(" ","A","Z"));
        System.out.println(l1);

        List l2 = e.stream().map(x -> "A"+ x +"Z").collect(Collectors.toList());
        System.out.println(l2);
    }
}
