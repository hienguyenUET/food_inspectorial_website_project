package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.BusinessType;
import com.example.food_inspectorate_website_project.entity.Premise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PremiseRepository extends JpaRepository<Premise, Integer> {
}
