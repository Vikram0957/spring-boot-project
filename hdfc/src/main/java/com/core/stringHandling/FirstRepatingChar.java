package com.core.stringHandling;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstRepatingChar {
    public static void main(String[] args) {
        String st = "Java is a programming language";
        Map<String, Long> map = Arrays.stream(st.split("")).filter(x -> !x.equals(" ")).collect(Collectors.groupingBy(x-> x,LinkedHashMap::new, Collectors.counting()));
        List<Map.Entry<String, Long>> list = map.entrySet().stream().filter(x -> x.getValue() == 1).toList();

        System.out.println(list.get(0));
    }
}
