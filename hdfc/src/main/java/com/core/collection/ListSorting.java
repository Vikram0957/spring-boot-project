package com.core.collection;

import java.util.List;

public class ListSorting {
    public static void main(String[] args) {
        List list = List.of(
                new Employee("A", 12),
                new Employee("B", 18),
                new Employee("c", 8),
                new Employee("D", 16),
                new Employee("K", 21)
        );

        List list1 = list.stream().sorted().toList();
        System.out.println(list1);

    }
}
