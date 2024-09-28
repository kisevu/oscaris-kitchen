package com.oscaris.kitchen.order_service.payload;
/*
*
@author ameda
@project order-service
*
*/

public class User {
    private int id;
    private String firstName;
    private String lastName;

    public void setId(int id){
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public int getId(){
        return  id;
    }
    public String getFirstName(){
        return  firstName;
    }
    public String getLastName(){
        return  lastName;
    }

    @Override
    public String toString(){
        return  "ID: "+ id +" First name: "+firstName+" LastName: "+lastName;
    }
}
