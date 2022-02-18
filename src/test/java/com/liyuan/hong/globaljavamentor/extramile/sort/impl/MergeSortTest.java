package com.liyuan.hong.globaljavamentor.extramile.sort.impl;

import com.liyuan.hong.globaljavamentor.extramile.sort.MergeSort;
import com.liyuan.hong.globaljavamentor.extramile.sort.SortTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MergeSortTest extends SortTest {
    public MergeSortTest() {
        super(new MergeSort());
    }
}
