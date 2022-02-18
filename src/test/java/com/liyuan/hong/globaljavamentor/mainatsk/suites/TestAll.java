package com.liyuan.hong.globaljavamentor.mainatsk.suites;

import com.liyuan.hong.globaljavamentor.mainatsk.cache.CacheTest;
import com.liyuan.hong.globaljavamentor.mainatsk.cache.impl.LFUCacheTest;
import com.liyuan.hong.globaljavamentor.mainatsk.cache.impl.LRUCacheTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LRUCacheTest.class,
        LFUCacheTest.class
})
public class TestAll {
}
