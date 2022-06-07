package com.example.food_inspectorate_website_project.entity.store;


import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.example.food_inspectorate_website_project.entity.address.Address;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * id: số id của cơ sở
 * name: tên cơ sở
 * phoneNum: số điện thoại của cơ sở
 * address: địa chỉ cơ sở
 * regNo: số chứng nhận đăng ký
 */

@Entity
@Table(name = "store")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "reg_no")
    private String regNo;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_type_id", referencedColumnName = "id", updatable = false)
    private BusinessType businessType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @Column(name = "inspected_date")
    private Date inspectedDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_id", referencedColumnName = "id")
    @JsonManagedReference
    private Certification certification;
    @OneToMany(mappedBy = "store")
    @JsonBackReference
    private List<StoreFoodList>storeFoodList;
    @Column(name = "qualify")
    private boolean qualify;
    @Column(name = "reason")
    private String reason;

}
