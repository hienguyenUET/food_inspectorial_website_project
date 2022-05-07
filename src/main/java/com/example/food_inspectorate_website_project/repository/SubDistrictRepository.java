package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Integer> {
    @Query(value = "SELECT * FROM SUB_DISTRICT WHERE sub_district_name =:subDistrictName", nativeQuery = true)
    SubDistrict getSubDistrict(@Param("subDistrictName")String subDistrictName);
}
