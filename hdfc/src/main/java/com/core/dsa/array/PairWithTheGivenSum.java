package com.core.dsa.array;

import java.util.Arrays;

public class PairWithTheGivenSum {
    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1, 8, 9};
        int target = 9;

        System.out.println("Approach 1");
        for (int a : arr) {
            for (int b : arr) {
                if (a + b == target) {
                    System.out.println("Sum of " + a + " and " + b + " is " + target);
                }
            }
        }

        System.out.println("Approach 2");
        int length = arr.length;
        for (int a = 0; a < length; a++) {
            for (int b = a; b < length; b++) {
                if (arr[a] + arr[b] == target) {
                    System.out.println("Sum of " + arr[a] + " and " + arr[b] + " is " + target);
                }
            }

        }

        System.out.println("Approach 3");// Using Binary search
        Arrays.sort(arr);
        for (int a = 0; a < length; a++) {
            int complement = target - arr[a];
            if (binarySearch(arr, a + 1, length - 1, complement)) {
                System.out.println("Sum of " + complement + " and " + arr[a] + " is " + target);
            }

        }

    }

    private static boolean binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2 ;
            if(arr[mid] == target)
                return true;
            if(arr[mid] < target)
                left = mid+1;
            else
                right = mid-1;
        }
        return false;
    }
}
