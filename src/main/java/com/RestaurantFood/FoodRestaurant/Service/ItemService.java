package com.RestaurantFood.FoodRestaurant.Service;

import com.RestaurantFood.FoodRestaurant.Dto.AddItemDto;
import com.RestaurantFood.FoodRestaurant.Model.CategoryModel;
import com.RestaurantFood.FoodRestaurant.Model.ItemModel;
import com.RestaurantFood.FoodRestaurant.Repository.CategoryRepo;
import com.RestaurantFood.FoodRestaurant.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepo repo;

    @Autowired
    CategoryRepo catRep;

    public CategoryModel findOrCreate(String name){
       return catRep.findByName(name).orElseGet(()->{
            CategoryModel newCat = new CategoryModel();
            newCat.setName(name);
            return catRep.save(newCat);
        });
    }
    public List<AddItemDto> getItems(){
        List<ItemModel> items = repo.findAll();
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
            im.setCategory(findOrCreate(catName));
            im.setName(i.getName());
            im.setPrice(i.getPrice());
            repo.save(im);
        }

    }

    public void updateItem( AddItemDto it) {
        ItemModel im = repo.findByName(it.getName()).orElse(null);
        if(im!=null){
            String catName = it.getCategoryName();
            im.setCategory(findOrCreate(catName));
            im.setName(it.getName());
            im.setPrice(it.getPrice());

            repo.save(im);
        }

    }

    public void deleteItem(int id) {
        repo.deleteById(id);
    }

    public void delete() {
        repo.deleteAll();
    }
}
