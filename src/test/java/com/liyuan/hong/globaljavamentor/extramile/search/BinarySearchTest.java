package com.liyuan.hong.globaljavamentor.extramile.search;

import com.liyuan.hong.globaljavamentor.extramile.IterativeBinarySearch;
import com.liyuan.hong.globaljavamentor.extramile.RecursiveBinarySearch;
import com.liyuan.hong.globaljavamentor.util.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public abstract class BinarySearchTest {

    @Parameterized.Parameter(0)
    public int[] arr;

    @Parameterized.Parameter(1)
    public int number;

    public BinarySearchTest() {
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        int[] arr1 = getArr(10);
        int num1 = getNumber(10);
        data.add(new Object[]{arr1, num1});
        data.add(new Object[]{getArr(50), getNumber(50)});
        data.add(new Object[]{getArr(100), getNumber(100)});
        data.add(new Object[]{getArr(200), getNumber(200)});
        data.add(new Object[]{getArr(500), getNumber(500)});
        return data;
    }

//    @Test
//    public void testSetup() {
//        System.out.println(number + ", " + arr);
//        Assert.assertFalse(number != 0);
//    }

    @Test
    public void test() {
        Assert.assertNotNull(arr);
        StopWatch watch = new StopWatch();
        watch.start();
        int iter = IterativeBinarySearch.find(number, arr);
        watch.stop();
        long iterElapsedTime = watch.getElapsed();

        watch.reset();
        watch.start();
        int recur = RecursiveBinarySearch.find(number, arr);
        watch.stop();
        long recurElapsedTime = watch.getElapsed();
        System.out.println("iterativesearchTime:" + iterElapsedTime);
        System.out.println("recursivesearchTime:" + recurElapsedTime);
        Assert.assertEquals(iter, number);
        Assert.assertEquals(recur, number);
    }

    public static int[] getArr(int length) {
        return Stream.iterate(0, x -> x = x + 1).limit(length).mapToInt(x -> x).toArray();
    }

    public static int getNumber(int length) {
        return new Random().nextInt(length);
    }
}
