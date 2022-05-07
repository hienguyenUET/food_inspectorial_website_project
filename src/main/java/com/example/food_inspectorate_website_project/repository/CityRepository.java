package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value = "SELECT * FROM City WHERE city_name=:cityName", nativeQuery = true)
    City getCity(@Param("cityName")String cityName);
}
