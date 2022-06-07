package com.example.food_inspectorate_website_project.service.implementation.food;

import com.example.food_inspectorate_website_project.entity.food.Qualify;
import com.example.food_inspectorate_website_project.repository.QualifyRepository;
import com.example.food_inspectorate_website_project.service.service.food.QualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualifyServiceImpl implements QualifyService {
    @Autowired
    private QualifyRepository qualifyRepository;

    @Override
    public Qualify findById(int id) {
        return qualifyRepository.findById(id);
    }

    @Override
    public Qualify findByQualify(boolean qualify) {
        return qualifyRepository.findByQualified(qualify);
    }
}
