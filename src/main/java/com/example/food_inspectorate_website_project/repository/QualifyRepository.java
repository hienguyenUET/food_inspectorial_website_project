package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.food.Qualify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QualifyRepository extends JpaRepository<Qualify, Integer> {
    Qualify findById(int id);
    Qualify findByQualified(boolean qualified);
}
