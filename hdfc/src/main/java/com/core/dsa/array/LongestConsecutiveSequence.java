package com.core.dsa.array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
//    Input: nums = [100,4,200,1,3,2]
//    Output: 4
    public static void main(String[] args) {
        int [] arr = {0,3,7,2,5,8,4,6,0,1};

        Set<Integer> set = new HashSet<>();
        for (int a: arr){
            set.add(a);
        }

        int length = 0;

        for(int num : set){
            if(!set.contains(num-1)){
                int currentLongest = 1;
                int temp = num;
                while (set.contains(temp+1)){
                    currentLongest++;
                    temp++;
                }
                length = Math.max(currentLongest, length);
            }
        }

        System.out.println(length);
    }
}
