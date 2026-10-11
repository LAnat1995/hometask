package com.github.LAnat.orders.web;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.orders.app.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent event) {
        String orderId = orderService.createOrder(event);
        return ResponseEntity.ok("Заказ отправлен: " + orderId);
    }
}
