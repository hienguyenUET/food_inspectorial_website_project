package com.example.food_inspectorate_website_project.service.implementation.store;

import com.example.food_inspectorate_website_project.entity.store.Certification;
import com.example.food_inspectorate_website_project.repository.CertificationRepository;
import com.example.food_inspectorate_website_project.service.service.store.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CertificationServiceImpl implements CertificationService {
    @Autowired
    private CertificationRepository repository;

    @Override
    public void save(Certification certification) {
        repository.save(certification);
    }


    @Override
    public Certification findById(int id) {
        Certification certification = null;
        if (repository.findById(id).isPresent()) {
            certification = repository.findById(id).get();
        }
        return certification;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Certification> findByExpirationDateBefore(Date expirationDate) {
        return repository.findByExpirationDateBefore(expirationDate);

    }
}
