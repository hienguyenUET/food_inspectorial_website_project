package com.example.food_inspectorate_website_project.entity;

import com.example.food_inspectorate_website_project.entity.food.Food;
import com.example.food_inspectorate_website_project.entity.food.Qualify;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Table(name = "store_food_list")
@Entity
public class StoreFoodList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "store_id")
    @JsonManagedReference
    private Store store;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "food_id")
    @JsonManagedReference
    private Food food;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "food_status_id")
    @JsonManagedReference
    private Status status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "qualify_id")
    @JsonManagedReference
    private Qualify qualify;
    @Column(name = "reason")
    private String reason;
    @Column(name = "is_inspected")
    private boolean inspected;
    @Column(name = "date")
    private Date dateForInspection;
}
