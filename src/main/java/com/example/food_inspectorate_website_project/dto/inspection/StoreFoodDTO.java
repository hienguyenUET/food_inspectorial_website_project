package com.example.food_inspectorate_website_project.dto.inspection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreFoodDTO {
    private String storeName;
    private List<FoodDTO> food;
}
