package com.liyuan.hong.globaljavamentor.extramile.sort;

import java.util.Arrays;

public class MergeSort implements SortInterface{

    @Override
    public int[] sort(int[] arr) {
        int[] res = mergeSort(arr, 0, arr.length - 1);
        return res;
    }

    public static int[] mergeSort(int[] arr, int start, int end) {
        int[] res;
        if (start == end) {
            res = new int[]{arr[start]};
        } else if (end - 1 == start) {
            res = merge(new int[]{arr[end]}, new int[]{arr[start]});
        } else {
            int mid = (end - start) / 2 + start;
            int[] subArr1 = mergeSort(arr, start, mid);
            int[] subArr2 = mergeSort(arr, mid + 1, end);
            res = merge(subArr1, subArr2);
        }
        return res;
    }

    public static int[] merge(int[] arr1, int[] arr2) {
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));
        int[] arr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (j < arr1.length && k < arr2.length) {
            if (arr1[j] > arr2[k]) {
                arr[i++] = arr2[k++];
            } else {
                arr[i++] = arr1[j++];
            }
        }
        while (j < arr1.length) {
            arr[i++] = arr1[j++];
        }
        while (k < arr2.length) {
            arr[i++] = arr2[k++];
        }
        return arr;
    }
}
