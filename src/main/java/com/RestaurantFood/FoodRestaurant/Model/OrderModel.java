package com.RestaurantFood.FoodRestaurant.Model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;


import java.util.List;

@Document
public class OrderModel {

    @DocumentId
    private String id;

    private String name;


    private String phoneNumber;



    private Priority priority;


    private List<String> itemNames;

    public List<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }



    private double totalPrice;

    public OrderModel() {
    }

    public OrderModel(String id, String name, String phoneNumber, Priority priority, double totalPrice, List<String> itemNames) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.priority = priority;
//        this.items = items;
        this.totalPrice = totalPrice;
        this.itemNames=itemNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
