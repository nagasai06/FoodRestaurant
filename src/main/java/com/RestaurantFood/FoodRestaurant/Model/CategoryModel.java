package com.RestaurantFood.FoodRestaurant.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
//import jakarta.persistence.*;

import java.util.List;

@Document
public class CategoryModel {
    @DocumentId
    private String id;
    private String name;

//    @OneToMany(mappedBy="categories")
//    @JsonIgnore
    //private List<ItemModel> items;

    public CategoryModel() {
    }

    public CategoryModel(String id, String name) {
        this.id = id;
        this.name = name;
        //this.items = items;
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

//    public List<ItemModel> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ItemModel> items) {
//        this.items = items;
//    }
}
