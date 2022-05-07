package com.example.food_inspectorate_website_project.entity;


import com.example.food_inspectorate_website_project.entity.address.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import javax.persistence.*;

/**
 * id: số id của cơ sở
 * name: tên cơ sở
 * phoneNum: số điện thoại của cơ sở
 * address: địa chỉ cơ sở
 * regNo: số chứng nhận đăng ký
 */

@Entity
@Table(name = "premise")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Premise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "premise_name")
    private String name;
    @Column(name = "premise_phone_number")
    private String phoneNumber;
    @Column(name = "premise_reg_no")
    private String regNo;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_type_id", referencedColumnName = "id", updatable = false)
    private BusinessType businessType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
