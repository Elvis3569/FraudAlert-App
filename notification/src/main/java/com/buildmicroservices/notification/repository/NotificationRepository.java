package com.buildmicroservices.notification.repository;

import com.buildmicroservices.notification.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Integer> {
}
