package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.StoreFoodList;

import java.util.List;


public interface StoreFoodListService {
    void save(StoreFoodList storeFoodList);

    List<StoreFoodList> findAll();

    List<StoreFoodList> findByStoreId(int id);

    StoreFoodList findByFoodIdAndStoreId(int food_id, int store_id);
}
