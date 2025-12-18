package com.dsa.array;

import java.util.Arrays;

public class MergeSorting {
    public static void main(String[] args) {
        int[] array = {9, 54, 23, 11, 19, 83};
        int lengthOfArray = array.length;

        divide(array, 0, lengthOfArray - 1);
        for (int j : array) {
            System.out.print(j + "\t");
        }
    }

    public static void divide(int[] array, int startingIndex, int endingIndex) {
        if (startingIndex >= endingIndex) {
            return;
        }

        int mid = startingIndex + (endingIndex - startingIndex) / 2;
        divide(array, startingIndex, mid);
        divide(array, mid + 1, endingIndex);
        conquer(array, startingIndex, mid, endingIndex);
    }

    private static void conquer(int[] array, int startingIndex, int mid, int endingIndex) {
        System.out.println(array);
        int[] newArray = new int[endingIndex - startingIndex + 1];
        int indexFirst = startingIndex;
        int indexSecond = mid + 1;
        int x = 0;

        while (indexFirst <= mid && indexSecond <= endingIndex) {
            if (array[indexFirst] <= array[indexSecond]) {
                newArray[x] = array[indexFirst];
                x++;
                indexFirst++;
            } else {
                newArray[x++] = array[indexSecond++];
            }

        }

        while (indexFirst <= mid) {
            newArray[x++] = array[indexFirst++];
            // x++; indexFirst++;
        }

        while (indexSecond <= endingIndex) {
            newArray[x++] = array[indexSecond++];
        }

        for (int i = 0, j = startingIndex; i < newArray.length; i++, j++) {
            array[j] = newArray[i];
        }
    }
}
