package com.RestaurantFood.FoodRestaurant.Repository;

import com.RestaurantFood.FoodRestaurant.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerModel, Integer> {

}
