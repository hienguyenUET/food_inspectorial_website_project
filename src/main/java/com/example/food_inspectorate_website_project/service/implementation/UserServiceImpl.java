package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.User;
import com.example.food_inspectorate_website_project.repository.UserRepository;
import com.example.food_inspectorate_website_project.service.service.UserService;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
