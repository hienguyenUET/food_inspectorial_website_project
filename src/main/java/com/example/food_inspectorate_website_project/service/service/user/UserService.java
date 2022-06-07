package com.example.food_inspectorate_website_project.service.service.user;

import com.example.food_inspectorate_website_project.entity.user.Role;
import com.example.food_inspectorate_website_project.entity.user.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    List<User> findByRole(Role role);
    User findById(int id);
}
