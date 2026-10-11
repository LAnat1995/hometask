package com.github.LAnat.payment.infra;

import com.github.LAnat.dto.OrderEvent;

public interface PaymentEventPublisher {
    void publishPayedOrder(OrderEvent event);
}
