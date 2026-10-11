package com.github.LAnat.orders.app;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.orders.infra.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public String createOrder(OrderEvent event) {
        if (event.getOrderId() == null || event.getOrderId().isBlank()) {
            event.setOrderId(UUID.randomUUID().toString());
        }
        event.setStatus("CREATED");

        log.info("[Orders] Создание заказа: {}", event);
        publisher.publishNewOrder(event);
        return event.getOrderId();
    }
}
