package com.oscaris.kitchen.order_service.payload;
/*
*
@author ameda
@project order-service
*
*/


public class Order {
    private int orderId;
    private String orderName;
    private String orderDate;

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    public void setOrderName(String orderName){
        this.orderName = orderName;
    }
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }
    public int getOrderId(){
        return orderId;
    }
    public String getOrderName(){
        return orderName;
    }
    public String getOrderDate(){
        return orderDate;
    }

    @Override
    public String toString(){
        return  "ID: "+ orderId +" First name: "+ orderName +" LastName: "+ orderDate;
    }
}
