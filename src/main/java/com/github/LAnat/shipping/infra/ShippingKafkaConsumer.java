package com.github.LAnat.shipping.infra;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.shipping.app.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShippingKafkaConsumer {

    private final ShippingService shippingService;

    @KafkaListener(topics = "payed_orders", concurrency = "${spring.kafka.listener.concurrency}")
    public void onPayedOrder(OrderEvent event) {
        shippingService.processShipping(event);
    }
}
