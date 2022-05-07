package com.example.food_inspectorate_website_project;

import com.example.food_inspectorate_website_project.entity.Premise;
import com.example.food_inspectorate_website_project.entity.address.Address;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.service.service.PremiseService;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class FoodInspectorateWebsiteProjectApplication implements CommandLineRunner {
    @Autowired
    private SubDistrictService service;
    @Autowired
    private PremiseService premiseService;

    public static void main(String[] args) {
        SpringApplication.run(FoodInspectorateWebsiteProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {

    }
}
