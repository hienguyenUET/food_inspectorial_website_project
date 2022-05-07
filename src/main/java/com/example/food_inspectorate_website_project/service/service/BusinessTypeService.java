package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.BusinessType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessTypeService {
    BusinessType getTypeOfBusiness(@Param("typeOfBusiness")String typeOfBusiness);
}
