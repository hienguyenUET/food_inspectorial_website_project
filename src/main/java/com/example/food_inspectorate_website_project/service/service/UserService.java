package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);

}
