package com.example.food_inspectorate_website_project.entity.address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number")
    private int number;
    @Column(name = "alley")
    private String alley;
    @Column(name = "street", nullable = false)
    private String street;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_district_id", referencedColumnName = "id", updatable = false)
    private SubDistrict subDistrict;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", number=" + number +
                ", alley='" + alley + '\'' +
                ", street='" + street + '\'' +
                ", subDistrict=" + subDistrict +
                '}';
    }
}
