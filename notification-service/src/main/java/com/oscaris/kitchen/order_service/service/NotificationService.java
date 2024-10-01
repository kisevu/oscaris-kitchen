package com.oscaris.kitchen.order_service.service;/*
*
@author ameda
@project notification-service
*
*/


import com.oscaris.kitchen.order_service.payload.Notification;
import com.oscaris.kitchen.order_service.payload.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public interface NotificationService {

    Notification saveNotification(NotificationRequest request);
}
