package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.food.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
