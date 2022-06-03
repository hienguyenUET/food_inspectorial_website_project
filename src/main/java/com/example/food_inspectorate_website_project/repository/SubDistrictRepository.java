package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer> {
    @Query(value = "SELECT * FROM SUB_DISTRICT WHERE sub_district_name =:subDistrictName", nativeQuery = true)
    SubDistrict getSubDistrict(@Param("subDistrictName")String subDistrictName);
    List<SubDistrict> findByDistrict(District districts);
    List<SubDistrict> findByDistrict_DistrictName(String district_districtName);
    SubDistrict findBySubDistrictName(String subDistrictName);
}
