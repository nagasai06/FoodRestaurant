package com.RestaurantFood.FoodRestaurant.Controller;


import com.RestaurantFood.FoodRestaurant.Dto.AddItemDto;
import com.RestaurantFood.FoodRestaurant.Model.ItemModel;
import com.RestaurantFood.FoodRestaurant.Service.ItemService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService service;
    @GetMapping("/")
    public ResponseEntity<List<AddItemDto>> getItems(){

        return new ResponseEntity<>(service.getItems(), HttpStatus.OK);
    }
    @GetMapping("/debug/credentials")
    public String checkCredentials() {
        try {
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            String info = "Credentials class: " + credentials.getClass().getName();

            if (credentials instanceof ServiceAccountCredentials) {
                String email = ((ServiceAccountCredentials) credentials).getClientEmail();
                info += ", Service Account Email: " + email;
            }

            return info;
        } catch (Exception e) {
            return "Failed to get credentials: " + e.getMessage();
        }
    }


    @PostMapping("/addItems")
    public ResponseEntity<Void> addItems(@RequestBody List<AddItemDto> it){
        service.addItems(it);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateItem")
    public ResponseEntity<Void> updateItem(@RequestBody AddItemDto it){
        service.updateItem(it);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id){
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> delete(){
        service.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
