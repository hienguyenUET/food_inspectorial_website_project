package com.example.food_inspectorate_website_project.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class InspectedFoodResult {
    private int id;
    private String name;
    private Date inspectedDate;
    private String reason;
}
