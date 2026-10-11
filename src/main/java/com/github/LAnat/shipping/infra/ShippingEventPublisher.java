package com.github.LAnat.shipping.infra;

import com.github.LAnat.dto.OrderEvent;

public interface ShippingEventPublisher {
    void publishSentOrder(OrderEvent event);
}
