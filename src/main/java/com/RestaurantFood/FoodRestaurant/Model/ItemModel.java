package com.RestaurantFood.FoodRestaurant.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Or;


@Entity
@Table(name = "item_model", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Enter a name")
    @Column(unique=true)
    private String name;
    @NotNull(message="need Price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private CategoryModel categories;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private OrderModel order;

    public CategoryModel getCategories() {
        return categories;
    }

    public void setCategories(CategoryModel categories) {
        this.categories = categories;
    }

//    public OrderModel getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderModel order) {
//        this.order = order;
//    }

    public ItemModel() {
    }

    public ItemModel(int id, String name, double price, CategoryModel categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        //this.order=order;
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
