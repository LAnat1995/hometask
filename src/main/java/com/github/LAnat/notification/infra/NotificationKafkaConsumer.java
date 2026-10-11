package com.github.LAnat.notification.infra;

import com.github.LAnat.dto.OrderEvent;
import com.github.LAnat.notification.app.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationKafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "sent_orders", concurrency = "${spring.kafka.listener.concurrency}")
    public void onSentOrder(OrderEvent event) {
        notificationService.sendDeliveryNotification(event);
    }
}
