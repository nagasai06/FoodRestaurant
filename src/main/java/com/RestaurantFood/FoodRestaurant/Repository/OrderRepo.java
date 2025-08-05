package com.RestaurantFood.FoodRestaurant.Repository;

import com.RestaurantFood.FoodRestaurant.Model.OrderModel;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends FirestoreReactiveRepository<OrderModel> {
}
