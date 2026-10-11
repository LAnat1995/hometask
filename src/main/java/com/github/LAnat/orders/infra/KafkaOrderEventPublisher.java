package com.github.LAnat.orders.infra;

import com.github.LAnat.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaOrderEventPublisher implements OrderEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(KafkaOrderEventPublisher.class);

    @Override
    public void publishNewOrder(OrderEvent event) {
        kafkaTemplate.send("new_orders", event.getOrderId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("[Orders] Ошибка отправки заказа {}: {}",
                                event.getOrderId(), ex.getMessage(), ex);
                    } else {
                        log.info("[Orders] Заказ {} отправлен в партицию {}",
                                event.getOrderId(),
                                result.getRecordMetadata().partition());
                    }
                });
    }
}
