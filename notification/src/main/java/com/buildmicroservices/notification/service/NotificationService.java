package com.buildmicroservices.notification.service;

import com.buildmicroservices.clients.request.NotificationRequest;
import com.buildmicroservices.notification.model.NotificationModel;
import com.buildmicroservices.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationRequest notificationRequest){
        NotificationModel notificationModel = NotificationModel.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender("Elvis")
                .message(notificationRequest.message())
                .senAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notificationModel);
    }
}
