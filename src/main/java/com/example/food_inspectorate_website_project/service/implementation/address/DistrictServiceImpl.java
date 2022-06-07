package com.example.food_inspectorate_website_project.service.implementation.address;

import com.example.food_inspectorate_website_project.entity.address.City;
import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.repository.DistrictRepository;
import com.example.food_inspectorate_website_project.service.service.address.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public void save(District district) {
        districtRepository.save(district);
    }

    @Override
    public List<District> findByCity(City city) {
        return districtRepository.findByCity(city);
    }

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrict(String districtName) {
       return districtRepository.getDistrict(districtName);
    }
}
