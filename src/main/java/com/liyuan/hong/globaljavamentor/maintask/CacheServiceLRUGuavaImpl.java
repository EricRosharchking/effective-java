package com.liyuan.hong.globaljavamentor.maintask;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.liyuan.hong.globaljavamentor.maintask.service.CacheService;
import com.liyuan.hong.globaljavamentor.model.Item;

import java.util.concurrent.TimeUnit;

public class CacheServiceLRUGuavaImpl implements CacheService {
    private int MAX_SIZE = 100000;
    private final int LAST_ACCESSED = 5000;
    private CacheLoader<String, Item> loader;
    private LoadingCache<String, Item> cache;
    private RemovalListener<String, Item> listener;

    public CacheServiceLRUGuavaImpl() {
        loader = new CacheLoader<String, Item>() {
            @Override
            public Item load(String s) throws InterruptedException {
                Thread.sleep(50);
                return new Item(s);
            }
        };

        listener = (x) -> {
            if (x.wasEvicted()) {
                System.out.println(x.getCause().name());
            }
        };

        cache = CacheBuilder.newBuilder()
                .recordStats()
                .maximumSize(MAX_SIZE)
                .expireAfterAccess(LAST_ACCESSED, TimeUnit.MILLISECONDS)
                .removalListener(listener)
                .build(loader);
    }

    @Override
    public void put(Item item) {
        cache.put(item.getName(), item);
    }

    @Override
    public Item get(String name) {
        return cache.getUnchecked(name);
    }

    @Override
    public void getStats() {
        cache.stats();
    }

    @Override
    public Item remove(String name) {
        Item item = cache.getUnchecked(name);
        cache.invalidate(name);
        return item;
    }
}
