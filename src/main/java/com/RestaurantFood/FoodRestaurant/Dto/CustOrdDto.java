package com.RestaurantFood.FoodRestaurant.Dto;

import com.RestaurantFood.FoodRestaurant.Model.CustomerModel;
import com.RestaurantFood.FoodRestaurant.Model.OrderModel;

public class CustOrdDto {
    private CustomerModel custModel;
    private OrderModel ordModel;


    public CustomerModel getCustModel() {
        return custModel;
    }

    public void setCustModel(CustomerModel custModel) {
        this.custModel = custModel;
    }

    public OrderModel getOrdModel() {
        return ordModel;
    }

    public void setOrdModel(OrderModel ordModel) {
        this.ordModel = ordModel;
    }
}
