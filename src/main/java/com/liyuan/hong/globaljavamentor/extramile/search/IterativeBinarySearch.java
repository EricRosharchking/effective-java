package com.liyuan.hong.globaljavamentor.extramile.search;

import java.util.Arrays;

public class IterativeBinarySearch {

    public static int find(int number, int[] arr) {
        int res = -1;
        if (arr == null || arr.length == 0 || arr[0] > number || arr[arr.length-1] < number) {
            return res;
        }
        int start = 0;
        int end = arr.length -1;

        int mid = (end - start) / 2 + start;
        while (mid > start && mid < end) {
            if (arr[mid] > number) {
                end = mid;
                mid = (end - start) / 2 + start;
            } else if (arr[mid] < number){
                start = mid;
                mid = (end - start) / 2 + start;
            } else {
                start = mid;
                end = mid;
            }
        }
        if (arr[start] == number) {
            res = start;
        } else if (arr[end] == number) {
            res = end;
        }

        return res;
    }
}
