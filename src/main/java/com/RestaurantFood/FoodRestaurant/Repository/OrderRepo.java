package com.RestaurantFood.FoodRestaurant.Repository;

import com.RestaurantFood.FoodRestaurant.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
}
