package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.store.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM business_type WHERE type_of_business =:typeOfBusiness")
    BusinessType getTypeOfBusiness(@Param("typeOfBusiness")String typeOfBusiness);
}
