package com.core.stringHandling;

public class MemoryAllocationTest {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("Hello")+" "+"word";
        String s3 = "Hello word";
        System.out.println(s2== s3);
    }
}
