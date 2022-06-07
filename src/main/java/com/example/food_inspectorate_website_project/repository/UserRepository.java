package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.user.Role;
import com.example.food_inspectorate_website_project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findByRole(Role role);
}
