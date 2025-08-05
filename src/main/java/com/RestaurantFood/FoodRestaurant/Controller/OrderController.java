package com.RestaurantFood.FoodRestaurant.Controller;


import com.RestaurantFood.FoodRestaurant.Dto.CustOrdDto;
import com.RestaurantFood.FoodRestaurant.Service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    FoodOrderService service;
    @PostMapping("/orderFood")
    public ResponseEntity<Void> orderFood(@RequestBody CustOrdDto cod){
        service.orderFood(cod);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
