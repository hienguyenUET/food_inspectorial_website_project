package com.example.food_inspectorate_website_project.service.implementation.store;

import com.example.food_inspectorate_website_project.entity.store.BusinessType;
import com.example.food_inspectorate_website_project.repository.BusinessTypeRepository;
import com.example.food_inspectorate_website_project.service.service.store.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {
    @Autowired
    private BusinessTypeRepository businessTypeRepository;
    @Override
    public BusinessType getTypeOfBusiness(String typeOfBusiness) {
        return businessTypeRepository.getTypeOfBusiness(typeOfBusiness);
    }
}