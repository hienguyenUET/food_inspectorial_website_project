package com.example.food_inspectorate_website_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * location: Địa bàn quản lý của người phụ trách
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("role")
    private String role;
    @JsonProperty("location")
    private String location;
}
