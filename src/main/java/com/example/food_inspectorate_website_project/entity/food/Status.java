package com.example.food_inspectorate_website_project.entity.food;

import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Table(name = "status")
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT default 1")
    private int id;
    private String status;
    @OneToMany(mappedBy = "status")
    @JsonBackReference
    private List<StoreFoodList> storeFoodLists;
}
