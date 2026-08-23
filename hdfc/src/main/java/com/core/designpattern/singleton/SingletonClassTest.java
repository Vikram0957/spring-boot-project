package com.core.designpattern.singleton;

public class SingletonClassTest {
    public static void main(String[] args) {
        Student s1 = Student.getStudent();
        Student s2 = Student.getStudent();

        System.out.println(s1);
        System.out.println(s2);
    }
}
