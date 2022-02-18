package com.liyuan.hong.globaljavamentor.util;

public class DoublyLinkedListNode<T> {
    private T t;
    private long lastAccessed;
    private DoublyLinkedListNode<T> prev;
    private DoublyLinkedListNode<T> next;

    public DoublyLinkedListNode(T t) {
        this.t = t;
        lastAccessed = System.currentTimeMillis();
        prev = null;
        next = null;
    }

    public T getItem() {
        return t;
    }

    public long getLastAccessed() {
        return lastAccessed;
    }

    public DoublyLinkedListNode<T> getNext() {
        return next;
    }

    public DoublyLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public void setPrev(DoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }

    public void setLastAccessed(long lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public void setItem(T t) {
        this.t = t;
    }
}
