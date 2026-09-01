package com.core.dsa.array;

public class SumOfTwoNumUsingTwoPointer {
    public static void main(String[] args) {
        int [] num = new int[]{2,11,5,15};
        int target = 7;
        int[] indexOfSum = getIndedxofSum(num, target);
        System.out.println(indexOfSum[0]+" "+indexOfSum[1]);
    }

    public static int[] getIndedxofSum(int [] arr , int target){
        int left = 0;
        int right = arr.length -1;
        int[] result = new int[]{-1,-1};
        while(left<right){
            if(arr[left] + arr[right] == target){
                return  new int[]{left, right};
            } else if (arr[left] + arr[right] < target) {
                left = left+1;
            } else {
                right = right-1;
            }
        }
        return result;
    }
}
