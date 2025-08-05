package com.RestaurantFood.FoodRestaurant.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @NotBlank(message = "Phone num mandatory")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Specify delivery type")
    private Priority priority;

    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ItemModel  > items = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "item_name")
    private List<String> itemNames;

    public List<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }

//    public void addItemr(ItemModel item){
//        items.add(item);
//        item.setOrder(this);
//    }

    private double totalPrice;

    public OrderModel() {
    }

    public OrderModel(int id, String name, String phoneNumber, Priority priority, double totalPrice, List<String> itemNames) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.priority = priority;
//        this.items = items;
        this.totalPrice = totalPrice;
        this.itemNames=itemNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

//    public List<ItemModel> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ItemModel> items) {
//        this.items = items;
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
