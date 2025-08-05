package com.RestaurantFood.FoodRestaurant.Dto;

public class AddItemDto {
    private String name;
    private double price;
    private String categoryName;

    public AddItemDto() {
    }

    public AddItemDto(String name, double price, String categoryName) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
