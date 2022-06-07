package com.example.food_inspectorate_website_project.service.service.user;

import com.example.food_inspectorate_website_project.entity.user.Role;
import org.springframework.data.repository.query.Param;

public interface RoleService {
    Role findByRoleName(@Param("roleName")String roleName);

}
