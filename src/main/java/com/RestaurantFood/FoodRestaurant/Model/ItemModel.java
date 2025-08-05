package com.RestaurantFood.FoodRestaurant.Model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
//import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import org.aspectj.weaver.ast.Or;


@Document
public class ItemModel {
    @DocumentId
    private String id;


    private String name;

    private double price;

    private CategoryModel categories;




    public ItemModel() {
    }

    public ItemModel(String id, String name, double price, CategoryModel categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        //this.order=order;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryModel getCategory() {
        return categories;
    }

    public void setCategory(CategoryModel categories) {
        this.categories = categories;
    }
}
