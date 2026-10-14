package com.core.streamapi.jcotd;

import java.util.List;
import java.util.stream.IntStream;

public class ReverseArray {
    public static void main(String[] args) {
        int[] a = {26,74,45,34,19,62,53,45, 19};
        List<Integer> b = List.of(26,74,45,34,19,62,53,45, 19);

        List<Integer> l1 = b.reversed();
        System.out.println("Reverse of List : "+l1);

        int [] arr =  reverseArray(a);
        for(int k : arr){
            System.out.print(k+" ");
        }
    }
    public static int[] reverseArray(int [] arr){
        return IntStream.range(0, arr.length).map(i -> arr[arr.length - 1- i]).toArray();
    }
}
