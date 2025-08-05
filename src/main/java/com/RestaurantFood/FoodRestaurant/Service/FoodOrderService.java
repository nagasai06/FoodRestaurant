package com.RestaurantFood.FoodRestaurant.Service;

import com.RestaurantFood.FoodRestaurant.Dto.CustOrdDto;
import com.RestaurantFood.FoodRestaurant.Model.CustomerModel;
import com.RestaurantFood.FoodRestaurant.Model.ItemModel;
import com.RestaurantFood.FoodRestaurant.Model.OrderModel;
import com.RestaurantFood.FoodRestaurant.Repository.CustomerRepo;
import com.RestaurantFood.FoodRestaurant.Repository.ItemRepo;
import com.RestaurantFood.FoodRestaurant.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderService {


    @Autowired
    OrderRepo order;

    @Autowired
    CustomerRepo customer;

    @Autowired
    ItemRepo item;


    public String orderFood(CustOrdDto cod){

        double totalPrice=0;
        CustomerModel cm = new CustomerModel();
        cm.setName(cod.getCustModel().getName());
        cm.setAddress(cod.getCustModel().getAddress());
        cm.setEmail(cod.getCustModel().getEmail());
        cm.setPhoneNumber(cod.getCustModel().getPhoneNumber());

        customer.save(cm);

        OrderModel om = new OrderModel();
        om.setName(cod.getCustModel().getName());
        om.setPhoneNumber(cod.getCustModel().getPhoneNumber());
        om.setPriority(cod.getOrdModel().getPriority());
        om.setItemNames(cod.getOrdModel().getItemNames());
        List<String> test = cod.getOrdModel().getItemNames();
        System.out.println(test);

        om.setPriority(cod.getOrdModel().getPriority());
        for(String str : cod.getOrdModel().getItemNames() ){
            System.out.println(str);
            ItemModel imr = item.findByName(str).orElseThrow(()-> new RuntimeException("Item Not found"));
            totalPrice+=imr.getPrice();
        }




        om.setTotalPrice(totalPrice);



        order.save(om);

        return "success";
    }
}
