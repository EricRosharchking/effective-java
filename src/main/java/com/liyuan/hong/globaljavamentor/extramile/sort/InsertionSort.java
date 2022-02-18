package com.liyuan.hong.globaljavamentor.extramile.sort;

import java.util.Arrays;

public class InsertionSort implements SortInterface{


    @Override
    public int[] sort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int index = 1; index < arr.length; index++) {
            for (int position = 0; position < index; position++) {
                if (arr[position] > arr[index]) {
                    insert(arr, position, index);
                    position = index;
                }
//                System.out.println(Arrays.toString(arr));
            }
        }
        return arr;
    }

    public static void insert(int[] arr, int position, int index) {
        for (int i = index; i > position; i--) {
            swap(arr, i, i-1);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
