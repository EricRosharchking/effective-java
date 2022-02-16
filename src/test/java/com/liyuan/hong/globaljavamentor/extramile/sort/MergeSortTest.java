package com.liyuan.hong.globaljavamentor.extramile.sort;

import com.liyuan.hong.globaljavamentor.extramile.MergeSort;

import java.util.Arrays;

public abstract class MergeSortTest {

    public static void main(String[] args) {
        int[] arr= {7,6,4,5,3,2,1};
        int[] res = MergeSort.sort(arr);
        System.out.println(Arrays.toString(res));
    }
}
