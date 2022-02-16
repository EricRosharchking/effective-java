package com.liyuan.hong.globaljavamentor.util;

import com.liyuan.hong.globaljavamentor.extramile.IterativeBinarySearch;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class TestClass {

    public static void main(String[] args) {
        int[] arr = getArr(10);
        int num = getNumber(10);
        System.out.println(num+", "+Arrays.toString(arr));
        int iter = IterativeBinarySearch.find(num, arr);
        System.out.println(iter);
    }


    public static int getNumber(int length) {
        return new Random().nextInt(length);
    }
    public static int[] getArr(int length) {
        return Stream.iterate(0, x -> x=x+1).limit(length).mapToInt(x -> x).toArray();
    }
}
