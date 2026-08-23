package com.github.LAnat.dataaggregator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {

    private double fetchPrice(String productName) throws InterruptedException {
        simulateDelay();
        simulateError();
        return 899.99;  //some price
    }

    private String fetchDescription(String productName) throws InterruptedException {
        simulateDelay();
        simulateError();
        return "Описание"; //some description
    }

    private double fetchRating(String productName) throws InterruptedException {
        simulateDelay();
        simulateError();
        return 4.7; //some rating
    }

    private void simulateDelay() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
    }

    private void simulateError() {
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            throw new RuntimeException("Сервис временно недоступен");
        }
    }

    public ProductInfo aggregateProductInfo(String productName) {
        CompletableFuture<Double> priceFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchPrice(productName);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> 0.0);

        CompletableFuture<String> descFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchDescription(productName);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> "Нет данных");

        CompletableFuture<Double> ratingFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        return fetchRating(productName);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(ex -> 0.0);

        return CompletableFuture
                .allOf(priceFuture, descFuture, ratingFuture)
                .thenApply(v -> new ProductInfo(
                        productName,
                        priceFuture.join(),
                        descFuture.join(),
                        ratingFuture.join()
                ))
                .join();
    }
}
