package com.liyuan.hong.globaljavamentor.model;

public class Item implements Comparable<Item>{
    private String name;

    public Item() {

    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Item item) {
        return name.compareTo(item.getName());
    }
}
