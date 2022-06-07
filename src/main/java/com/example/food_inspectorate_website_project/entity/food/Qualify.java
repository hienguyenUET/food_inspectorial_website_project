package com.example.food_inspectorate_website_project.entity.food;

import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Table(name = "qualify")
@Entity
public class Qualify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "qualify")
    private boolean qualified;
    @OneToMany(mappedBy = "qualify")
    @JsonBackReference
    private List<StoreFoodList> storeFoodList;
}
