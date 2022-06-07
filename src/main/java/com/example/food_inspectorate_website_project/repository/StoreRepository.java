package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByRegNo(String regNo);
    List<Store> findByCertificationNull();
    List<Store> findByStatus(Status status);
    List<Store> findByStatusStatus(String status_status);
    List<Store> findByAddress_SubDistrict(SubDistrict address_subDistrict);
    List<Store> findByStatusStatusAndAddress_SubDistrict(String status_status, SubDistrict address_subDistrict);
}
