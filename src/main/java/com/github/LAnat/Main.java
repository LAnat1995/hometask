package com.github.LAnat;

import com.github.LAnat.dataaggregator.DataAggregator;
import com.github.LAnat.dataaggregator.ProductInfo;

public class Main {
    public static void main(String[] args) {
        DataAggregator aggregator = new DataAggregator();

        long start = System.currentTimeMillis();
        ProductInfo product = aggregator.aggregateProductInfo("Ноутбук");
        long end = System.currentTimeMillis();

        System.out.println(product);
        System.out.println("Время выполнения: " + (end - start) + " мс");
    }
}