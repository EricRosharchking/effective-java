package com.liyuan.hong.globaljavamentor.util;

import java.util.Objects;

public class Occurrence {
    String name;
    Integer count;
    public Occurrence(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void addFrequency() {
        this.count ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occurrence that = (Occurrence) o;
        return name.equals(that.name) && count.equals(that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }
}
