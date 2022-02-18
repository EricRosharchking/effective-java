package com.liyuan.hong.globaljavamentor.mainatsk.cache;

import com.liyuan.hong.globaljavamentor.model.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

public abstract class CacheTest {

    @Parameterized.Parameter
    public List<Item> items;

    @Parameterized.Parameters
    public List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{});
        return data;
    }

    @Test
    public void test() {
        Assert.assertTrue(true);
    }
}
