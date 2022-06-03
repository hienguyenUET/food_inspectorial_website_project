package com.example.food_inspectorate_website_project;

import com.example.food_inspectorate_website_project.service.service.StoreService;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class FoodInspectorateWebsiteProjectApplication {
    @Autowired
    private SubDistrictService service;
    @Autowired
    private StoreService storeService;

    public static UserDetails userDetails;

    public static void main(String[] args) {
        SpringApplication.run(FoodInspectorateWebsiteProjectApplication.class, args);
    }

}
