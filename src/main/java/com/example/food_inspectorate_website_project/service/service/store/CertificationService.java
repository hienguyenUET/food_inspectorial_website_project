package com.example.food_inspectorate_website_project.service.service.store;

import com.example.food_inspectorate_website_project.entity.store.Certification;

import java.sql.Date;
import java.util.List;

public interface CertificationService {
    void save(Certification certification);
    Certification findById(int id);
    void deleteById(int id);
    List<Certification> findByExpirationDateBefore(Date expirationDate);
}
