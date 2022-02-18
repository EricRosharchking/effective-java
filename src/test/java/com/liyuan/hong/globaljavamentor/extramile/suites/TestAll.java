package com.liyuan.hong.globaljavamentor.extramile.suites;

import com.liyuan.hong.globaljavamentor.extramile.search.impl.BinarySearchTestImpl;
import com.liyuan.hong.globaljavamentor.extramile.sort.impl.BinarySearchSortTest;
import com.liyuan.hong.globaljavamentor.extramile.sort.impl.InsertionSortTest;
import com.liyuan.hong.globaljavamentor.extramile.sort.impl.MergeSortTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BinarySearchTestImpl.class,
        BinarySearchSortTest.class,
        InsertionSortTest.class,
        MergeSortTest.class
})
public class TestAll {
}
