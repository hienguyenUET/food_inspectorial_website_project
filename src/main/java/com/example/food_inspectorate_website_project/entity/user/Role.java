package com.example.food_inspectorate_website_project.entity.user;

import com.example.food_inspectorate_website_project.enum_list.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name")
    private ERole name;
}
