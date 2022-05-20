package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.Account;

public interface AccountService {
    void save(Account account);
    void findByUsername(String username);
}
