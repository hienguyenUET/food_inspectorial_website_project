package com.example.food_inspectorate_website_project.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QualifiedFood {
    @JsonProperty("qualified")
    private boolean qualified;
    @JsonProperty("reason")
    private String reason;
}
