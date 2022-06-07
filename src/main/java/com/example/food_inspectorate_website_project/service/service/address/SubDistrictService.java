package com.example.food_inspectorate_website_project.service.service.address;

import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubDistrictService {
    void save(SubDistrict subDistrict);
    SubDistrict getSubDistrict(@Param("subDistrictName")String subDistrictName);
    List<SubDistrict> findByDistrict(District districts);
    List<SubDistrict> findByDistrict_DistrictName(String district_districtName);
    SubDistrict findBySubDistrictName(String subDistrictName);

}
