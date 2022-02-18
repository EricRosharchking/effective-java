package com.liyuan.hong.globaljavamentor.extramile.sort;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public abstract class SortTest {

    @Parameterized.Parameter
    public int[] arr;

    private SortInterface sortImpl;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{getArrWithRandomInt(10)});
        data.add(new Object[]{getArrWithRandomInt(50)});
        data.add(new Object[]{getArrWithRandomInt(100)});
        data.add(new Object[]{getArrWithRandomInt(200)});
        data.add(new Object[]{getArrWithRandomInt(500)});
        return data;
    }

    public SortTest(SortInterface sortImpl) {
        this.sortImpl = sortImpl;
    }

    @Test
    public void test() {
        int[] res = sortImpl.sort(arr);
        Assert.assertTrue(isSorted(res));
    }

    private static int[] getArrWithRandomInt(int limit) {
        int max = 1000;
        Random rd = new Random();
        return Stream.generate(() -> rd.nextInt(max)).limit(limit).mapToInt(x -> x).toArray();
    }

    private static boolean isSorted(int[] arr) {
        for (int i = arr.length - 1; i > arr.length; i--) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }
}
