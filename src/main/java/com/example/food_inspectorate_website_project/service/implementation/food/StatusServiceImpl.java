package com.example.food_inspectorate_website_project.service.implementation.food;

import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.repository.StatusRepository;
import com.example.food_inspectorate_website_project.service.service.food.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status findById(int id) {
        return statusRepository.findById(id).get();
    }
}
