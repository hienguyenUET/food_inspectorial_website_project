package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.BusinessType;
import com.example.food_inspectorate_website_project.entity.Premise;
import com.example.food_inspectorate_website_project.repository.PremiseRepository;
import com.example.food_inspectorate_website_project.service.service.PremiseService;
import org.springframework.stereotype.Service;

@Service
public class PremiseServiceImpl implements PremiseService {

    private final PremiseRepository premiseRepository;

    public PremiseServiceImpl(PremiseRepository premiseRepository) {
        this.premiseRepository = premiseRepository;
    }

    @Override
    public void save(Premise premise) {
        premiseRepository.save(premise);
    }
}
