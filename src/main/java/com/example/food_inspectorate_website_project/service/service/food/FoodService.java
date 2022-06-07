package com.example.food_inspectorate_website_project.service.service.food;

import com.example.food_inspectorate_website_project.entity.food.Food;

import java.util.List;

public interface FoodService {
    Food findByName(String name);
    boolean existsByName(String name);
    void save(Food food);
    List<Food> findAll();
}
