package com.core.streamapi.md;

import java.util.Arrays;

public class FrequencyOfWordInArray {
    public static void main(String[] args) {
        String[] arr = {"hello", "hello vikram", "hi hello", "bye", "say hello to me"};
        long count = Arrays.stream(arr).flatMap(a -> Arrays.stream(a.split("\\s+")))
                .filter(word -> word.equals("hello")).count();
        System.out.println(count);
    }
}
