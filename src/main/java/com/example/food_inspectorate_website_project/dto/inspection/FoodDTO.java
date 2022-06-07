package com.example.food_inspectorate_website_project.dto.inspection;

import com.example.food_inspectorate_website_project.entity.food.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private int id;
    private String name;
    private String status;
    private boolean qualified;
    private boolean inspected;
    private String reason;
    private Date inspectedDate;
}
