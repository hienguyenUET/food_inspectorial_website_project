package com.example.food_inspectorate_website_project.dto.inspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class InspectedProcessDTO {
    @JsonProperty("id")
    private int storeId;
}
