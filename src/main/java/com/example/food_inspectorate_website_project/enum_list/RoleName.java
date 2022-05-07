package com.example.food_inspectorate_website_project.enum_list;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
public enum RoleName {
    ROLE_ADMIN("ADMIN"),
    ROLE_SPECIALIST("SPECIALIST");

    private final String roleType;

    RoleName(String roleType) {
        this.roleType = roleType;
    }
}
