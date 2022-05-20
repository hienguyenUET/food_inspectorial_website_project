package com.example.food_inspectorate_website_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account"
, uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "create_date")
    private Date createdDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", updatable = false)
    private User user;
}
