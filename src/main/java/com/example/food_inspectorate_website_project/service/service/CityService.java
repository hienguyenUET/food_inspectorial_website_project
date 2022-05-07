package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.address.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityService {
    void save(City city);
    City getCity(String cityName);
}
