package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import org.springframework.data.repository.query.Param;

public interface SubDistrictService {
    void save(SubDistrict subDistrict);
    SubDistrict getSubDistrict(@Param("subDistrictName")String subDistrictName);
}
