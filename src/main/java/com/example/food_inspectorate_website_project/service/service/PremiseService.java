package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.BusinessType;
import com.example.food_inspectorate_website_project.entity.Premise;
import org.springframework.data.repository.query.Param;

public interface PremiseService {
    void save(Premise premise);

}
