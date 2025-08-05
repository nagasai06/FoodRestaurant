package com.RestaurantFood.FoodRestaurant.Repository;

import com.RestaurantFood.FoodRestaurant.Model.CategoryModel;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface CategoryRepo extends FirestoreReactiveRepository<CategoryModel> {
    Mono<CategoryModel> findByName(String name);
}
