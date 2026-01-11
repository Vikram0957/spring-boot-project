package com.core.oops;

public class CloneTesting {
    public static void main(String[] args) throws CloneNotSupportedException {
        OuterClass outerClass = new OuterClass(1, "Vikram", new Address("Khohar"));
        OuterClass clone = outerClass;
        System.out.println(outerClass);
        System.out.println(clone);

        outerClass.setAddress(new Address("Satna"));
        System.out.println("After Cloning!");
        System.out.println(outerClass);
        System.out.println(clone);
    }
}
