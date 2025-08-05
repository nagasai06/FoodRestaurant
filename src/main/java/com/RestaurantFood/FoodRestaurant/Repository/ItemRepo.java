package com.RestaurantFood.FoodRestaurant.Repository;

import com.RestaurantFood.FoodRestaurant.Model.ItemModel;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ItemRepo extends FirestoreReactiveRepository<ItemModel> {
    Mono<ItemModel> findByName(String name);
}
