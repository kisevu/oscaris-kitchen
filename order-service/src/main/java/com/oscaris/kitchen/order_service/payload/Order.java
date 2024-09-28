package com.oscaris.kitchen.order_service.payload;
/*
*
@author ameda
@project order-service
*
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String orderName;
    private String orderDate;
    private BigDecimal price;

    @Override
    public String toString(){
        return  "ID: "+ orderId +" First name: "+ orderName +" LastName: "+ orderDate;
    }
}
