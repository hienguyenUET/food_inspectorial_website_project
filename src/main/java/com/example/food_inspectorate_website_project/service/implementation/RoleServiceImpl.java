package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.Role;
import com.example.food_inspectorate_website_project.repository.RoleRepository;
import com.example.food_inspectorate_website_project.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
