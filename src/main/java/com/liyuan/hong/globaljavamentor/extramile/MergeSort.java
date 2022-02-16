package com.liyuan.hong.globaljavamentor.extramile;

import java.util.Arrays;

public class MergeSort {

    public static int[] sort(int[] arr) {
        int[] res = mergeSort(arr, 0, arr.length - 1);
        return res;
    }

    public static int[] mergeSort(int[] arr, int l, int r) {
        int[] res;
        if (l == r) {
            res = new int[]{arr[l]};
        } else if (r - 1 == l) {
            res = merge(new int[]{arr[r]}, new int[]{arr[l]});
        } else {
            int m1 = (r - l) / 2 + l;
            int[] subArr1 = mergeSort(arr, l, m1);
            int[] subArr2 = mergeSort(arr, m1 + 1, r);
            res = merge(subArr1, subArr2);
        }
        return res;
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
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
