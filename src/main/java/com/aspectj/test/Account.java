package com.aspectj.test;

import com.aspectj.test.aspects.PerformanceMonitor;

public class Account {
    private int balance = 20;

    @PerformanceMonitor("accountMonitor")
    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}