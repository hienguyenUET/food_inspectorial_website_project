package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.repository.DistrictRepository;
import com.example.food_inspectorate_website_project.service.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public void save(District district) {
        districtRepository.save(district);
    }

    @Override
    public District getDistrict(String districtName) {
       return districtRepository.getDistrict(districtName);
    }
}
