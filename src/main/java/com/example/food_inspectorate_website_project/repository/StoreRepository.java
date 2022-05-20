package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
