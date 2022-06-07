package com.example.food_inspectorate_website_project.service.service.address;

import com.example.food_inspectorate_website_project.entity.address.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

public interface CountryService {
    Country getCountry(String countryName);
}
