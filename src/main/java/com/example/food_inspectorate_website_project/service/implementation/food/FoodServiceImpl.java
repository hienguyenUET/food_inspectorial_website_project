package com.example.food_inspectorate_website_project.service.implementation.food;

import com.example.food_inspectorate_website_project.entity.food.Food;
import com.example.food_inspectorate_website_project.repository.FoodRepository;
import com.example.food_inspectorate_website_project.service.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food findByName(String name) {
        return foodRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return foodRepository.existsByName(name);
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

}
