package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.address.District;

public interface DistrictService {
    void save(District district);
    District getDistrict(String districtName);
}
