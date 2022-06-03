package com.example.food_inspectorate_website_project.service.service;

import com.example.food_inspectorate_website_project.entity.Certification;

import java.sql.Date;
import java.util.List;

public interface CertificationService {
    void save(Certification certification);
    Certification findById(int id);
    void deleteById(int id);
    List<Certification> findByExpirationDateBefore(Date expirationDate);
}
