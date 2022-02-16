package com.liyuan.hong.globaljavamentor.maintask;

import com.liyuan.hong.globaljavamentor.maintask.service.CacheService;
import com.liyuan.hong.globaljavamentor.model.Item;
import com.liyuan.hong.globaljavamentor.util.OccurComparator;
import com.liyuan.hong.globaljavamentor.util.Occurrence;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Max Size = 100 000;
 * Eviction policy;
 * Time-based on last access (5 seconds);
 * Removal listener;
 * Just add to log of removed entry;
 * Give statistic to user;
 * Average time spent for putting new values into the cache;
 * Number of cache evictions;
 * Support concurrency.
 */

public class CacheServiceLRUImpl implements CacheService {
    private Map<String, Item> cache;
    private PriorityQueue<Occurrence> occurrences;
    private OccurComparator<Occurrence> comparator;
    private Map<String, Integer> frequency;
    private final int MAX_SIZE = 100000;
    private final int LAST_ACCESSED = 5000;
    @Override
    public void put(Item item) {

    }

    @Override
    public Item get(String name) {
        return null;
    }

    @Override
    public void getStats() {

    }

    @Override
    public Item remove(String name) {
        return null;
    }
}
