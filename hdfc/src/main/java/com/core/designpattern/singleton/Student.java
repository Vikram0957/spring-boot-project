package com.core.designpattern.singleton;

public class Student {
    private static Student s = new Student();
    private Student(){
    }

    public static Student getStudent(){
        if(s==null) {
            s = new Student();
            return s;
        }
        return s;
    }
}
