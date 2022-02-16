package com.liyuan.hong.globaljavamentor.extramile.sort;

import com.liyuan.hong.globaljavamentor.extramile.InsertionSort;

import java.util.Arrays;

public abstract class InsertionSortTest {
    public static void main(String[] args) {

        int[] arr= {7,6,4,5,3,2,1};
        InsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
