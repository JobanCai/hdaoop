package com.cjp.bigdata.hadoop;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> result = new ArrayList<Integer>();

    private static boolean flag = true;

    private static int partition(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= temp) high--;
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp) low++;
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    private static void minK(int k, int[] arr, int low, int high) {
        if (flag) {
            int temp = partition(arr, low, high);
            if (temp == k - 1) {
                for (int i = 0; i < k; i++) {
                    result.add(arr[i]);
                }
                flag = false;
            } else if (temp < k - 1) {
                minK(k, arr, temp + 1, high);
            } else {
                minK(k, arr, low, temp - 1);
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {8, 1, 2, 15, 4, 7, 6};
        minK(5, arr, 0, arr.length - 1);
        System.out.println(result);
    }


}
