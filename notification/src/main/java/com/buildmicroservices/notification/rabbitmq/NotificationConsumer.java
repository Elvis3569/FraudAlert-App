package com.buildmicroservices.notification.rabbitmq;

import com.buildmicroservices.clients.request.NotificationRequest;
import com.buildmicroservices.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue",notificationRequest);
        notificationService.saveNotification(notificationRequest);
    }


}