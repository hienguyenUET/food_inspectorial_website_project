package com.example.food_inspectorate_website_project.dto.inspection;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class InspectedStoreDTO {
    private int id;
    private String status;
    private Date inspectedDate;
    private String regNo;
    private boolean qualify;
    private String reason;
}
