package com.core.dsa.array;

public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        // First common approach
        int [] arr = {7,1,5,3,6,4};
        System.out.println(maxProfit(arr));

        // Second approach, best greedy solution
        System.out.println(maxProfitOptimized(arr));
    }

    public static int maxProfit(int[] arr) {
        int max = 0;
        int size = arr.length;
        int buyDay = -1;
        int sellDay = -1;
        for(int a = 0; a< size; a++){
            for(int b = a+1; b<size; b++){
                if(arr[a]<arr[b] && max < arr[b] - arr[a]){
                    max = arr[b] - arr[a];
                    sellDay = b;
                    buyDay = a;
                }
            }
        }
        System.out.println("Buy stock on day : "+buyDay+" and sell it on day : "+sellDay );
        return max;
    }

    public static int maxProfitOptimized(int [] arr){
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int price : arr){
            minPrice = Math.min(minPrice, price);
            int profit = price - minPrice;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }
}
