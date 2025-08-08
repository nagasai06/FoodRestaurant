package com.RestaurantFood.FoodRestaurant.Service;

import com.RestaurantFood.FoodRestaurant.Dto.AddItemDto;
import com.RestaurantFood.FoodRestaurant.Model.CategoryModel;
import com.RestaurantFood.FoodRestaurant.Model.ItemModel;
import com.RestaurantFood.FoodRestaurant.Repository.CategoryRepo;
import com.RestaurantFood.FoodRestaurant.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepo repo;

    @Autowired
    CategoryRepo catRep;

    public Mono<CategoryModel> findOrCreate(String name){
       return catRep.findByName(name).switchIfEmpty(Mono.defer(()->{
           CategoryModel newCat = new CategoryModel();
           newCat.setName(name);
           return catRep.save(newCat);
       }));
    }

//    Updated change from mono to list
//    Test1
//    tt
    //tt
    public List<AddItemDto> getItems(){
        List<ItemModel> items = repo.findAll().collectList().block();
        return items.stream().map(item -> new AddItemDto(
                item.getName(),
                item.getPrice(),
                item.getCategory() !=null ? item.getCategory().getName() : null

    )).collect(Collectors.toList());
    }

    public void addItems(List<AddItemDto> it) {
        for(AddItemDto i:it){
            ItemModel im = new ItemModel();
            String catName = i.getCategoryName();
            System.out.println(catName);
            im.setCategory(findOrCreate(catName).block());
            System.out.println(i.getName());
            im.setName(i.getName());
            im.setPrice(i.getPrice());
            System.out.println(i.getPrice());
            repo.save(im).block();
        }

    }

    public void updateItem( AddItemDto it) {
        ItemModel im = repo.findByName(it.getName()).switchIfEmpty(Mono.error(new RuntimeException("Item odel not found"))).block();
        if(im!=null){
            String catName = it.getCategoryName();
            im.setCategory(findOrCreate(catName).block());
            im.setName(it.getName());
            im.setPrice(it.getPrice());

            repo.save(im).block();
        }

    }

    public void deleteItem(String id) {
        repo.deleteById(id).block();
    }

    public void delete() {
        repo.deleteAll().block();
    }
}
