package com.example.food_inspectorate_website_project.service.implementation.user;

import com.example.food_inspectorate_website_project.entity.user.Role;
import com.example.food_inspectorate_website_project.entity.user.User;
import com.example.food_inspectorate_website_project.repository.UserRepository;
import com.example.food_inspectorate_website_project.service.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id).get();
    }
}