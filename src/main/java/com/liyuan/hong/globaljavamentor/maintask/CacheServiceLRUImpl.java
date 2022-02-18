package com.liyuan.hong.globaljavamentor.maintask;

import com.liyuan.hong.globaljavamentor.maintask.service.CacheService;
import com.liyuan.hong.globaljavamentor.model.Item;
import com.liyuan.hong.globaljavamentor.util.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
    private Map<String, DoublyLinkedListNode<Item>> cache;
    private DoublyLinkedListNode<Item> head, tail;
    private int MAX_SIZE = 100000;
    private final int LAST_ACCESSED = 5000;
    private final Set<String> removedItems;
    private int evictions;
    private int puts;
    private long putTimeMillis;

    public CacheServiceLRUImpl() {
        cache = new ConcurrentHashMap<>();
        head = null;
        tail = null;
        removedItems = new HashSet<>();
    }

    public CacheServiceLRUImpl(int size) {
        this();
        MAX_SIZE = size;
    }

    @Override
    public void put(Item item) {
        long currentTime = System.currentTimeMillis();
        DoublyLinkedListNode<Item> node = cache.get(item.getName());
        if (node != null) {
            moveToHead(node, currentTime);
            node.setItem(item);
            cache.put(item.getName(), node);
        } else {
            putAsHead(item);
        }
        putTimeMillis += (System.currentTimeMillis() - currentTime);
        puts ++;
    }

    @Override
    public Item get(String name) {
        long currentTime = System.currentTimeMillis();
        DoublyLinkedListNode<Item> node = cache.get(name);
        if (node != null) {
            moveToHead(node, currentTime);
        } else {
            node = putAsHead(new Item(name));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
        return node.getItem();
    }

    @Override
    public void getStats() {
        System.out.println(puts);
        System.out.println(putTimeMillis/puts/1000);
        System.out.println(evictions);
        System.out.println(removedItems);
    }

    @Override
    public Item remove(String name) {
        DoublyLinkedListNode<Item> node = cache.get(name);
        if (node != null) {
            node.getNext().setPrev(node.getPrev());
            node.getPrev().setNext(node.getNext());
        }
        return node == null ? null : node.getItem();
    }

    private synchronized void evictExpired() {
        long currentTime = System.currentTimeMillis();
        while (tail.getLastAccessed() >= currentTime - LAST_ACCESSED) {
            removedItems.add(tail.getItem().getName());
            evictions++;
            tail = tail.getPrev();
        }
        tail.getNext().setPrev(null);
        tail.setNext(null);
    }

    private synchronized void evictOldest() {
        while (cache.size() >= MAX_SIZE) {
            cache.remove(tail.getItem().getName());
            tail = tail.getPrev();
        }
        tail.getNext().setPrev(null);
        tail.setNext(null);
    }

    private synchronized void moveToHead(DoublyLinkedListNode<Item> node, long currentTime) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setLastAccessed(currentTime);
        head.setPrev(node);
        node.setNext(head);
        node.setPrev(null);
        head = node;
    }

    public synchronized DoublyLinkedListNode<Item> putAsHead(Item item) {
        DoublyLinkedListNode<Item> node = new DoublyLinkedListNode<>(item);
        cache.put(item.getName(), node);
        head.setPrev(node);
        node.setNext(head);
        head = node;
        return node;
    }
}
