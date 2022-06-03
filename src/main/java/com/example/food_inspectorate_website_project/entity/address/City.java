package com.example.food_inspectorate_website_project.entity.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int name_id;
    @Column(name = "city_name")
    private String city;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "id", updatable = false)
    private Country country;
//    @OneToMany(mappedBy = "city")
//    private List<District> districts = new ArrayList<>();
}