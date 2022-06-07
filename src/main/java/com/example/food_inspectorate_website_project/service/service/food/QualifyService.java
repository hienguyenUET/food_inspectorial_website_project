package com.example.food_inspectorate_website_project.service.service.food;

import com.example.food_inspectorate_website_project.entity.food.Qualify;

public interface QualifyService {
    Qualify findById(int id);
    Qualify findByQualify(boolean qualify);
}
