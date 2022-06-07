package com.example.food_inspectorate_website_project.service.implementation.store;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.repository.StoreRepository;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
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
    public List<Store> findByCertificationNull() {
        return storeRepository.findByCertificationNull();
    }

    @Override
    public List<Store> findByStatus(Status status) {
        return storeRepository.findByStatus(status);
    }

    @Override
    public List<Store> findByStatusStatus(String status_status) {
        return storeRepository.findByStatusStatus(status_status);
    }

    @Override
    public List<Store> findByAddress_SubDistrict(SubDistrict address_subDistrict) {
        return storeRepository.findByAddress_SubDistrict(address_subDistrict);
    }

    @Override
    public List<Store> findByStatusStatusAndAddress_SubDistrict(String status_status, SubDistrict address_subDistrict) {
        return storeRepository.findByStatusStatusAndAddress_SubDistrict(status_status, address_subDistrict);
    }
}
