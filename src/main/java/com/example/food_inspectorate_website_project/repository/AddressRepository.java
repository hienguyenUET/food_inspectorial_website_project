package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
