package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM ROLE WHERE role_name =:roleName")
    Role findByRoleName(@Param("roleName")String roleName);
}
