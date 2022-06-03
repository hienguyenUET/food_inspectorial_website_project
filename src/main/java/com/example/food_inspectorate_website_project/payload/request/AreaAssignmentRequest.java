package com.example.food_inspectorate_website_project.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AreaAssignmentRequest {
    @JsonProperty("id")
    private int id;
    @JsonProperty("sub_district_name")
        private String subDistrictName;
}
