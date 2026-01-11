package com.core.dsa.array;

import java.util.Arrays;

public class BubbleSorting {
    public static void main(String[] args) {
        int[] arr = {9, 54, 23, 11, 99, 78, 56, 41, 6, 9, 23, 92, 41, 39, 59, 19, 83, 73, 55, 23};
        int length = arr.length;
        int sortingLength = length - 1;

        for (int a = 0; a < length; a++) {
            for (int b = a; b < sortingLength; b++) {
                if (arr[a] > arr[b + 1]) {
                    int temp = arr[a];
                    arr[a] = arr[b + 1];
                    arr[b + 1] = temp;
                }
            }
        }
        for (int b : arr) {
            System.out.print(b + "\t");
        }
    }
}
