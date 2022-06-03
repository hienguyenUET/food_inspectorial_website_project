package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByRegNo(String regNo);
    List<Store> findByCertificationNull();
}
