package com.github.LAnat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private String status; // CREATED, PAID, SENT
}
