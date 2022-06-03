package com.example.food_inspectorate_website_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@Getter
@Table(name = "certificate")
@ToString
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "cert_number")
    private String certificationNumber;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "certification", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
    targetEntity = Store.class)
    @JsonBackReference
    private Store store;
}
