package com.oscaris.kitchen.order_service.repository;/*
*
@author ameda
@project notification-service
*
*/

import com.oscaris.kitchen.order_service.payload.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
