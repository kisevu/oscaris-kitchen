package com.oscaris.kitchen.order_service.service.serviceImpl;/*
*
@author ameda
@project notification-service
*
*/

import com.oscaris.kitchen.order_service.payload.Notification;
import com.oscaris.kitchen.order_service.payload.NotificationRequest;
import com.oscaris.kitchen.order_service.repository.NotificationRepository;
import com.oscaris.kitchen.order_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public Notification saveNotification(NotificationRequest request) {
        Notification obj = Notification.builder()
                .message("Successfully persisted your event Kevin.")
                .orderItem(request.getOrderItem())
                .build();
        return repository.save(obj);
    }
}
