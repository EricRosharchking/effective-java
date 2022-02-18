package com.liyuan.hong.globaljavamentor.extramile.search;

public class RecursiveBinarySearch {

    public static int find(int number, int[] arr) {
        return binarySearch(number, arr, 0, arr.length - 1);
    }

    public static int binarySearch(int number, int[] arr, int start, int end) {
        if (arr == null || arr.length == 0 || arr[start] > number || arr[end] < number) {
            return -1;
        }
        int mid = (end - start) / 2 + start;
        if (arr[mid] == number) {
            return mid;
        } else if (mid == start || mid == end) {
            return -1;
        } else {
            if (arr[mid] > number) {
                return binarySearch(number, arr, start, mid);
            } else if (arr[mid] < number) {
                return binarySearch(number, arr, mid, end);
            }
        }
        return -1;
    }
}
