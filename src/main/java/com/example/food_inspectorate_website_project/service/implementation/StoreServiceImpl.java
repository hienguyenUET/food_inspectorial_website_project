package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.Store;
import com.example.food_inspectorate_website_project.repository.StoreRepository;
import com.example.food_inspectorate_website_project.service.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void save(Store store) {
        storeRepository.save(store);
    }

    @Override
    public Store findById(int id) {
        return storeRepository.findById(id).get();
    }

    @Override
    public Store findByRegNo(String regNo) {
        return storeRepository.findByRegNo(regNo);
    }

    @Override
    public void deleteByRegNo(String regNo) {
        storeRepository.findByRegNo(regNo);
    }

    @Override
    public List<Store> findByCertificationNull() {
        return storeRepository.findByCertificationNull();
    }
}
