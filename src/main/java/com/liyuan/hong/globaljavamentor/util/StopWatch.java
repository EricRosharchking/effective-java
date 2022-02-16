package com.liyuan.hong.globaljavamentor.util;

public class StopWatch {
    private long startTime;
    private long endTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        endTime = System.currentTimeMillis();
    }

    public long getElapsed() {
        return endTime - startTime;
    }

    public void reset() {
        endTime = startTime;
    }
}
