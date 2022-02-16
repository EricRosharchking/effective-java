package com.liyuan.hong.globaljavamentor.util;

import java.util.Comparator;

public class OccurComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        Occurrence t1 = (Occurrence) o1;
        Occurrence t2 = (Occurrence) o2;
        return t1.getCount().compareTo(t2.getCount());
    }
}
