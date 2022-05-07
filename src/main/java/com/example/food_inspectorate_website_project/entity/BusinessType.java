package com.example.food_inspectorate_website_project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "business_type")
@Table(name = "business_type")
@Setter
@Getter
public class BusinessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_of_business")
    private String businessType;
}
