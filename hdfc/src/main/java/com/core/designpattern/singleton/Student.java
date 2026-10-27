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

    // Safe for multi-threaded env
    private volatile  static  Student student;

    public static Student getStudentSynchronized(){
        if(student == null){
            synchronized (String.class){
                if(student == null){
                    student = new Student();
                    return student;
                }
            }
        }
        return student;
    }
}
