package com.core.streamapi.jcotd;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfCharacterOrString {
    public static void main(String[] args) {
        String d = "Java is a programming language";
        List<Character> f = List.of('a','c','a','d','a', 'c');

        Map<String, Long> result = Arrays.stream(d.split("")).filter(x -> ! x.equals(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

        Map<Character, Long> res = f.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(res);
    }
}
