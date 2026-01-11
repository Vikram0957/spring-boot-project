package com.core.oops;

public class ParentChildTest {
    public static void main(String[] args) {
        Child c = new Child();
        c.childSpecific();
        c.defaultMethod();
        c.staticMethod();
        c.instanceMethod();
    }
}
