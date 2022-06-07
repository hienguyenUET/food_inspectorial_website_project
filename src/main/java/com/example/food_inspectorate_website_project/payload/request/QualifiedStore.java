package com.example.food_inspectorate_website_project.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QualifiedStore {
    private boolean qualify;
    private String reason;
}
