package com.example.food_inspectorate_website_project.entity.address;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Getter
public final class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String countryName;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
