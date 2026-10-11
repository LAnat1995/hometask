package com.github.LAnat.notification.app;

import com.github.LAnat.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendDeliveryNotification(OrderEvent event) {
        log.info("[Notification] Уведомление пользователю по заказу: {}. Статус: {}",
                event.getOrderId(), event.getStatus());
    }
}
