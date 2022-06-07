package com.example.food_inspectorate_website_project.repository;


import com.example.food_inspectorate_website_project.entity.store.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    Certification findByCertificationNumber(String certificationNumber);
    List<Certification> findByExpirationDateBefore(Date expirationDate);
}
