package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.Store;

import java.util.List;

public interface StoreService {
    void save(Store store);
    List<Store> findAll();
    Store findById(int id);
    Store findByRegNo(String regNo);
    void deleteByRegNo(String regNo);
    List<Store> findByCertificationNull();
}
