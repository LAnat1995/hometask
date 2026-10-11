package com.github.LAnat.shipping.infra;

import com.github.LAnat.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaShippingEventPublisher implements ShippingEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Override
    public void publishSentOrder(OrderEvent event) {
        kafkaTemplate.send("sent_orders", event.getOrderId(), event);
    }
}
