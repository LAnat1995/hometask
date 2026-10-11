package com.github.LAnat.payment.app;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.payment.infra.KafkaPaymentEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaPaymentEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public void processPayment(OrderEvent event) {
        log.info("[Payment] Обработка оплаты заказа: {}, поток: {}",
                event.getOrderId(), Thread.currentThread().getName());

        // Имитация бизнес-логики оплаты
        event.setStatus("PAID");

        log.info("[Payment] Оплата выполнена: {}", event);
        publisher.publishPayedOrder(event);
    }
}
