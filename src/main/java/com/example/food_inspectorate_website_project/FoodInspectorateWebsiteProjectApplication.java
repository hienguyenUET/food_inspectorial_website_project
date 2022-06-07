package com.example.food_inspectorate_website_project;


import com.example.food_inspectorate_website_project.entity.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class FoodInspectorateWebsiteProjectApplication {
    public static User user = new User();
    public static UserDetails userDetails;

    public static void main(String[] args) {
        SpringApplication.run(FoodInspectorateWebsiteProjectApplication.class, args);
    }
}
