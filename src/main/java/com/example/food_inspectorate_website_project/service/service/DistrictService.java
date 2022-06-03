package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.address.City;
import com.example.food_inspectorate_website_project.entity.address.District;

import java.util.List;

public interface DistrictService {
    void save(District district);
    List<District> findAll();
    List<District> findByCity(City city);
    District getDistrict(String districtName);
}
