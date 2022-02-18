package com.liyuan.hong.globaljavamentor.maintask;

import com.liyuan.hong.globaljavamentor.maintask.service.CacheService;
import com.liyuan.hong.globaljavamentor.model.Item;
import com.liyuan.hong.globaljavamentor.util.OccurComparator;
import com.liyuan.hong.globaljavamentor.util.Occurrence;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Max Size = 100 000;
 * Eviction policy;
 * Removal listener;
 * Just add to log of removed entry;
 * Give statistic to user;
 * Average time spent for putting new values into the cache;
 * Number of cache evictions;
 */


public class CacheServiceLFUImpl implements CacheService {
    private final Map<String, Item> cache;
    private final PriorityQueue<Occurrence> occurrences;
    private final Map<String, Integer> frequency;
    private int MAX_SIZE = 100000;
    private final Set<String> removedItems;
    private int evictions;
    private int puts;
    private long putTimeMillis;

    public CacheServiceLFUImpl() {
        cache = new ConcurrentHashMap<>();
        OccurComparator<Occurrence> comparator = new OccurComparator<>();
        occurrences = new PriorityQueue<>(comparator);
        frequency = new ConcurrentHashMap<>();
        removedItems = new LinkedHashSet<>();
    }

    public CacheServiceLFUImpl(int size) {
        this();
        MAX_SIZE = size;
    }

    @Override
    public void put(Item item) {
        long initTime = System.currentTimeMillis();
        if (cache.containsKey(item.getName())) {
            // in cache means in occurrences priorityQ
            // 1. update cache
            putToCache(item);
            // 2. add one hit to the frequency map
            addHitToFrequencyMap(item.getName());
            // 3. add one hit to the occurrences priorityQ
            addHitToOccurrencesQ(item, true);
        } else {
            // not in cache means not in occurrences priorityQ
            // 1. add one hit to the frequency map
            addHitToFrequencyMap(item.getName());
            // 2. if hit in frequency map is higher than last one in priorityQ
            // then add to cache and add to priorityQ
            addHitToOccurrencesQ(item, false);
        }
        putTimeMillis += (System.currentTimeMillis() - initTime);
    }

    @Override
    public Item get(String name) {
        Item item = cache.get(name);
        if (item == null) {
            // not in cache means not in occurrences priorityQ
            // 1. get item from DB
            item = new Item(name);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                // not in cache means not in occurrences priorityQ
                // 1. add one hit to the frequency map
                addHitToFrequencyMap(name);
                // 2. if hit in frequency map is higher than last one in priorityQ
                // then add to cache and add to priorityQ
                addHitToOccurrencesQ(item, false);
            }
        } else {
            // in cache means in occurrences priorityQ
            // 1. no need to update cache
//            putToCache(item);
            // 2. add one hit to the frequency map
            addHitToFrequencyMap(item.getName());
            // 3. add one hit to the occurrences priorityQ
            addHitToOccurrencesQ(item, true);
        }
        return item;
    }

    @Override
    public Item remove(String name) {
        Item item = cache.remove(name);
        if (item != null) {
            occurrences.remove(new Occurrence(name, frequency.get(name)));
            frequency.remove(name);
            removedItems.add(name);
        }
        return item;
    }

    @Override
    public synchronized void getStats() {
        System.out.println(puts);
        System.out.println(putTimeMillis / puts / 1000);
        System.out.println(evictions);
        System.out.println(removedItems);
    }

    private synchronized void addHitToFrequencyMap(String name) {
        int count = frequency.get(name);
        frequency.put(name, count + 1);
    }

    private synchronized void addHitToOccurrencesQ(Item item, boolean inCache) {
        if (inCache) {
//            update in OccurrencesQ, means it's already in cache
            occurrences.remove(new Occurrence(item.getName(), frequency.get(item.getName())-1));
            occurrences.add(new Occurrence(item.getName(), frequency.get(item.getName())));
        } else {
//            it's not in cache, adding in OccurrencesQ
//            means also adding to cache;
            putToCache(item);
            if (occurrences.isEmpty() || cache.size() < MAX_SIZE) {
                occurrences.add(new Occurrence(item.getName(), frequency.get(item.getName())));
            } else {
                if (frequency.get(item.getName()) >= occurrences.peek().getCount()) { // if exceeds MAX_SIZE then evict
                    if (cache.size() >= MAX_SIZE) {
                        evictFromCache();
                    }
                    occurrences.add(new Occurrence(item.getName(), frequency.get(item.getName())));
                }
            }
        }
    }

    //    update or add to cache
    private synchronized void putToCache(Item item) {
        puts++;
        cache.put(item.getName(), item);
    }

    //always remove the least frequently accessed item in the cache
    private synchronized void evictFromCache() {
        evictions++;
        Occurrence evicted = occurrences.poll();
        if (evicted == null)
            return;
        cache.remove(evicted.getName());
        removedItems.add(evicted.getName());
    }
}
