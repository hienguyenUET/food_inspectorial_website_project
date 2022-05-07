package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.repository.SubDistrictRepository;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubDistrictImpl implements SubDistrictService {
    @Autowired
    private SubDistrictRepository subDistrictRepository;

    @Override
    public void save(SubDistrict subDistrict) {
        subDistrictRepository.save(subDistrict);
    }

    @Override
    public SubDistrict getSubDistrict(String subDistrictName) {
        return subDistrictRepository.getSubDistrict(subDistrictName);
    }
}
