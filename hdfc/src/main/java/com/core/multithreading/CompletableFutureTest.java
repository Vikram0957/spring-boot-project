package com.core.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                return getFutureObject();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Main thread is running");

        try {

            String result = future.get();

            System.out.println("Result: " + result);

        } catch (Exception e) {

            e.printStackTrace();
        }
        CompletableFuture<String> result =
                future.thenApply(name -> name.toUpperCase());
        System.out.println(result.get());
    }

    public static String getFutureObject() throws InterruptedException {
        Thread.sleep(5000);
        return LocalDateTime.now().toString()+" vikram singh";
    }
}
