package com.example.food_inspectorate_website_project.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private final String TYPE = "Bearer";
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
