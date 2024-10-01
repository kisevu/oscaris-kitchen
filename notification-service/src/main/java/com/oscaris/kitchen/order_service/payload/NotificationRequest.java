package com.oscaris.kitchen.order_service.payload;
/*
*
@author ameda
@project notification-service
*
*/


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private String message;
    private String orderItem;
}
