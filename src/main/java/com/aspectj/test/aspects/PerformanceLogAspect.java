package com.aspectj.test.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class PerformanceLogAspect {
    private Map<String, Statistics> callTimes = new ConcurrentHashMap<>();

    @Around("@annotation(performanceMonitor) && execution(@com.aspectj.test.aspects.PerformanceMonitor * *.*(..))")
    public Object around(ProceedingJoinPoint pjp, PerformanceMonitor performanceMonitor) throws Throwable {
        final Statistics statistics = fetchStatistics(performanceMonitor);
        statistics.addStats();
        System.out.println(callTimes);
        return pjp.proceed();
    }

    private Statistics fetchStatistics(PerformanceMonitor performanceMonitor) {
        final Statistics statistics = callTimes.putIfAbsent(performanceMonitor.value(), new Statistics());
        return statistics == null ? callTimes.get(performanceMonitor.value()) : statistics;
    }
}
