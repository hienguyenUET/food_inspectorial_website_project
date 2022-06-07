package com.example.food_inspectorate_website_project.service.service.store;

import com.example.food_inspectorate_website_project.entity.store.BusinessType;
import org.springframework.data.repository.query.Param;

public interface BusinessTypeService {
    BusinessType getTypeOfBusiness(@Param("typeOfBusiness")String typeOfBusiness);
}
