package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.City;
import com.example.food_inspectorate_website_project.entity.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// get district from database
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT * FROM DISTRICT WHERE district_name =:districtName",nativeQuery = true)
    District getDistrict(@Param("districtName") String districtName);
    List<District> findByCity(City city);
}
