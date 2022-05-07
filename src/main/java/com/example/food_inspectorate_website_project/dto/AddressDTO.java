package com.example.food_inspectorate_website_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonProperty("district")
    private String district;
    @JsonProperty("sub_district")
    private String subDistrict;
    @JsonProperty("street")
    private String street;
    @JsonProperty("alley")
    private String alley;
    @JsonProperty("number")
    private int number;
}
