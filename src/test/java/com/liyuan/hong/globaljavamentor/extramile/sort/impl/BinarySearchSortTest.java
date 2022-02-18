package com.liyuan.hong.globaljavamentor.extramile.sort.impl;

import com.liyuan.hong.globaljavamentor.extramile.sort.BinarySearchSort;
import com.liyuan.hong.globaljavamentor.extramile.sort.SortTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BinarySearchSortTest extends SortTest {
    public BinarySearchSortTest() {
        super(new BinarySearchSort());
    }
}
