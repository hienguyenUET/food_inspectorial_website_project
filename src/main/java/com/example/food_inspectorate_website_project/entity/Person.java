package com.example.food_inspectorate_website_project.entity;

import com.example.food_inspectorate_website_project.enum_list.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Person(String firstName, String lastName, String roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
    }
    @Column(name = "role_name")
    private String roleName;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")
//    private Address addressToCheck;


}
