package com.core.oops;

public class Parent {
    public static void staticMethod(){
        System.out.println("This is static method from parent class");
    }

    void defaultMethod(){
        System.out.println("This is default method of Parent class");
    }

    public void instanceMethod(){
        System.out.println("This is instance method of child class");
    }
}
