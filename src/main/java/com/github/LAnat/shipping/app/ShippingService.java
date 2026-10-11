package com.github.LAnat.shipping.app;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.shipping.infra.ShippingEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(ShippingService.class);

    public void processShipping(OrderEvent event) {
        log.info("[Shipping] Отгрузка заказа: {}, поток: {}",
                event.getOrderId(), Thread.currentThread().getName());

        // Имитация бизнес-логики отгрузки
        event.setStatus("SENT");

        log.info("[Shipping] Заказ отгружен: {}", event);
        publisher.publishSentOrder(event);
    }
}
