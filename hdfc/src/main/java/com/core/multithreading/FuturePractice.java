package com.core.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturePractice {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> submit = executorService.submit(() -> {
           return getFutureData();
        });
        System.out.println("Main thread is running");

        boolean done = submit.isDone();
        System.out.println(done);

        Future.State state = submit.state();
        System.out.println(state.name());

        try {
            String s = submit.get();
            System.out.println("Result: "+ s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }

    public static String getFutureData() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return LocalDateTime.now().toString();
    }
}
