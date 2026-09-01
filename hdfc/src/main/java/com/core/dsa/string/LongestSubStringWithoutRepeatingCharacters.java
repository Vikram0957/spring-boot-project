package com.core.dsa.string;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcbb";
        // Tradition way
        int longest = findLongestString(s);
        System.out.println(longest);

        //optimized way
        System.out.println(findLongestSubStringOptimized(s));
    }

    private static int findLongestString(String s) {
        Set<String> set = new HashSet();
        int maxLength = 0;
        String[] arr = s.split("");
        int size = arr.length;
        Outer:
        for (int a = 0; a < size; a++) {
            int length = 0;
            for (int b = a; b < size; b++) {
                if (set.add(arr[b])) {
                    length++;
                    maxLength = Math.max(maxLength, length);
                } else {
                    set = new HashSet();
                    continue Outer;
                }
            }
        }
        return maxLength;
    }

    public static int findLongestSubStringOptimized(String st) {
        Set<String> set = new HashSet();
        int maxLength = 0;
        String[] arr = st.split("");
        int size = arr.length;

        int left = 0;
        for (int right = 0; right < size; right++) {
            while (set.contains(arr[right])) {
                set.remove(arr[left]);
                left++;
            }
            set.add(arr[right]);
            maxLength = Math.max(maxLength, (right - left) + 1);
        }
        return maxLength;
    }

}
