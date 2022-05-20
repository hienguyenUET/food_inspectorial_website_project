package com.example.food_inspectorate_website_project.entity;

import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", updatable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_district_id", referencedColumnName = "id", updatable = false)
    private SubDistrict subDistrict;
}
