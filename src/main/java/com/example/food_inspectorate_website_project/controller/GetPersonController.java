package com.example.food_inspectorate_website_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class GetPersonController {
    @GetMapping("/get")
    public ResponseEntity getSpecialist() {
        return null;
    }
}
