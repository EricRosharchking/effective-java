package com.liyuan.hong.globaljavamentor.extramile.sort.impl;

import com.liyuan.hong.globaljavamentor.extramile.sort.InsertionSort;
import com.liyuan.hong.globaljavamentor.extramile.sort.SortInterface;
import com.liyuan.hong.globaljavamentor.extramile.sort.SortTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class InsertionSortTest extends SortTest {
    public InsertionSortTest() {
        super(new InsertionSort());
    }
}
