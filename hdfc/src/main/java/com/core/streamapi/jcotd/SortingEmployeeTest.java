package com.core.streamapi.jcotd;

import java.util.Comparator;
import java.util.List;

public class SortingEmployeeTest {
    public static void main(String[] args) {
        Employee e1 = new Employee("Vikram", 100);
        Employee e2 = new Employee("Ruby", 150);
        Employee e3 = new Employee("Pushpendra", 90);
        Employee e4 = new Employee("Dilip", 500);
        Employee e5 = new Employee("Khelendra", 300);
        Employee e6 = new Employee("Singh", 500);

        List<Employee> list = List.of(e1, e2, e3, e4, e5, e6);
        List<Employee> list1 = list.stream().sorted(Comparator.comparingInt(Employee::getSalary)).toList();
        System.out.println(list1);
    }
}
