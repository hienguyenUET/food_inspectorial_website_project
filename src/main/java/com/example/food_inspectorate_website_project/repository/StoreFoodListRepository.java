package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreFoodListRepository extends JpaRepository<StoreFoodList, Integer> {
    List<StoreFoodList> findByStoreId(int id);
    StoreFoodList findByFoodIdAndStoreId(int food_id, int store_id);
}
