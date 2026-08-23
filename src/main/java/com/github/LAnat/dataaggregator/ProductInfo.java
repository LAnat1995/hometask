package com.github.LAnat.dataaggregator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductInfo {
    private final String name;
    private final double price;
    private final String description;
    private final double rating;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
