package com.example.food_inspectorate_website_project.service.implementation.address;

import com.example.food_inspectorate_website_project.entity.address.City;
import com.example.food_inspectorate_website_project.repository.CityRepository;
import com.example.food_inspectorate_website_project.service.service.address.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public City getCity(String cityName) {
        return cityRepository.getCity(cityName);
    }
}
