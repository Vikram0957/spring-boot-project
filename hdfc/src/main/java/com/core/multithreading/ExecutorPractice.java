package com.core.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorPractice implements Runnable{
    public static void main(String[] args) {
        ExecutorPractice executorPractice = new ExecutorPractice();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        fixedThreadPool.submit(executorPractice);
        fixedThreadPool.submit(executorPractice);
        fixedThreadPool.submit(executorPractice);
        fixedThreadPool.submit(executorPractice);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.submit(executorPractice);
        scheduledExecutorService.submit(executorPractice);
        scheduledExecutorService.submit(executorPractice);
        scheduledExecutorService.submit(executorPractice);


        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.submit(executorPractice);
        newCachedThreadPool.submit(executorPractice);
        newCachedThreadPool.submit(executorPractice);
        newCachedThreadPool.submit(executorPractice);


        ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        virtualThreadPerTaskExecutor.submit(executorPractice);
        virtualThreadPerTaskExecutor.submit(executorPractice);
        virtualThreadPerTaskExecutor.submit(executorPractice);
        virtualThreadPerTaskExecutor.submit(executorPractice);
    }

    @Override
    public void run() {
        System.out.println("Info of current Thread, Group: "+ Thread.currentThread().getThreadGroup()+" Name: "+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task completed by, Group: "+ Thread.currentThread().getThreadGroup()+" Name: "+Thread.currentThread().getName());
    }
}
