package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findByName(String name);
    boolean existsByName(String name);
}
