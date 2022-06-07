package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.inspection.FoodDTO;
import com.example.food_inspectorate_website_project.dto.inspection.StoreFoodDTO;
import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.example.food_inspectorate_website_project.entity.food.Food;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.service.service.StoreFoodListService;
import com.example.food_inspectorate_website_project.service.service.food.FoodService;
import com.example.food_inspectorate_website_project.service.service.food.StatusService;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private StoreFoodListService storeFoodListService;
    @Autowired
    private StatusService statusService;


//    @GetMapping("/add")
//    public void addFoodToStore() {
//        int storeId = 10;
//        Store store = storeService.findById(storeId);
//        List<String> foodList = new ArrayList<>(List.of(
//                "Gà nguyên con",
//                "Cá bạc má",
//                "Cá trứng",
//                "Thịt lợn ba chỉ hữu cơ",
//                "Cá mút đá",
//                "Bề bề",
//                "Lườn cá hồi",
//                "Nõn tôm"
//        ));
//        for (String food : foodList) {
//            if (!foodService.existsByName(food)) {
//                Food newFood = new Food();
//                newFood.setName(food);
//                foodService.save(newFood);
//            }
//            Food storeFood = foodService.findByName(food);
//            Status status = statusService.findById(1);
//            StoreFoodList storeFoodList = new StoreFoodList();
//            storeFoodList.setFood(storeFood);
//            storeFoodList.setStore(store);
//            storeFoodList.setStatus(status);
//            storeFoodListService.save(storeFoodList);
//        }
//    }

}
