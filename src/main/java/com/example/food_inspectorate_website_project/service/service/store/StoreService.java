package com.example.food_inspectorate_website_project.service.service.store;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;

import java.util.List;

public interface StoreService {
    void save(Store store);
    List<Store> findAll();
    Store findById(int id);
    Store findByRegNo(String regNo);
    List<Store> findByCertificationNull();
    List<Store> findByStatus(Status status);
    List<Store> findByStatusStatus(String status_status);
    List<Store> findByAddress_SubDistrict(SubDistrict address_subDistrict);
    List<Store> findByStatusStatusAndAddress_SubDistrict(String status_status, SubDistrict address_subDistrict);
}
