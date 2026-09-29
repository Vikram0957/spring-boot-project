package com.core.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingThreadCreation implements Runnable {
    public static void main(String[] args) {
        MultiThreadingThreadCreation multiThreadingThreadCreation = new MultiThreadingThreadCreation();

        Thread thread = new Thread(multiThreadingThreadCreation);
        thread.start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(multiThreadingThreadCreation);

        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        service.submit(multiThreadingThreadCreation);

        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.submit(()-> {
            System.out.println("Hi, I am doing submitted task!");
        });
    }


    @Override
    public void run() {
        System.out.println("This action is performing on separate thread :" + Thread.currentThread());
    }
}
