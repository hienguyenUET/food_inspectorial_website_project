package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.address.Country;
import com.example.food_inspectorate_website_project.repository.CountryRepository;
import com.example.food_inspectorate_website_project.service.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    final
    CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getCountry(String countryName) {
        return countryRepository.getCountry(countryName);
    }
}
