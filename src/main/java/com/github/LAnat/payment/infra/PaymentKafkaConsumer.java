package com.github.LAnat.payment.infra;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.payment.app.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "new_orders", concurrency = "${spring.kafka.listener.concurrency}")
    public void onNewOrder(OrderEvent event) {
        paymentService.processPayment(event);
    }
}
