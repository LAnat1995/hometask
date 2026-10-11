package com.github.LAnat.payment.infra;

import com.github.LAnat.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPaymentEventPublisher implements PaymentEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(KafkaPaymentEventPublisher.class);

    @Override
    public void publishPayedOrder(OrderEvent event) {
        kafkaTemplate.send("payed_orders", event.getOrderId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("[Payment] Ошибка публикации: {}", ex.getMessage(), ex);
                    } else {
                        log.info("[Payment] Событие оплаты отправлено в партицию {}",
                                result.getRecordMetadata().partition());
                    }
                });
    }
}
