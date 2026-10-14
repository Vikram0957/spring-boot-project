package com.core.stringHandling;

import java.util.List;

public class ImmutableClassTest {
    public static void main(String[] args) {
        ImmutableClass immutableClass1 = new ImmutableClass(12, "Vikram", List.of("Hello"));
        System.out.println(immutableClass1);

        ImmutableClass immutableClass2 = new ImmutableClass(12, "Vikram", List.of("Hello"));
        System.out.println(immutableClass2);
    }
}
