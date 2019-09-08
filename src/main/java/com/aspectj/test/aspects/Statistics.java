package com.aspectj.test.aspects;

import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {

    private AtomicInteger callTimes = new AtomicInteger();

    public void addStats() {
        callTimes.incrementAndGet();
    }

    public int getCallTimes() {
        return callTimes.get();
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "callTimes=" + callTimes +
                '}';
    }
}
