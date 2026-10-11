package com.github.LAnat.orders.infra;

import com.github.LAnat.dto.OrderEvent;

public interface OrderEventPublisher {
    void publishNewOrder(OrderEvent event);
}
