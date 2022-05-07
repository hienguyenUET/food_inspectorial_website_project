package com.example.food_inspectorate_website_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PremiseDTO {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("PhoneNumber")
    private String phoneNumber;
    @JsonProperty("RegNo")
    private String regNo;
    @JsonProperty("BusinessType")
    private String businessType;
    @JsonProperty("Address")
    private AddressDTO address;
}
