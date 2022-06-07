package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.repository.StoreFoodListRepository;
import com.example.food_inspectorate_website_project.service.service.StoreFoodListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreFoodListServiceImpl implements StoreFoodListService {
    @Autowired
    private StoreFoodListRepository listService;

    @Override
    public void save(StoreFoodList storeFoodList) {
        listService.save(storeFoodList);
    }

    @Override
    public List<StoreFoodList> findAll() {
        return listService.findAll();
    }

    @Override
    public List<StoreFoodList> findByStoreId(int id) {
        return listService.findByStoreId(id);
    }

    @Override
    public StoreFoodList findByFoodIdAndStoreId(int food_id, int store_id) {
        return listService.findByFoodIdAndStoreId(food_id, store_id);
    }
}
