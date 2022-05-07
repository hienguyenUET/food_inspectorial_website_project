package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "select * from Country where country_name =:countryName", nativeQuery = true)
    Country getCountry(@Param("countryName") String countryName);
}
