package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.FoodInspectorateWebsiteProjectApplication;
import com.example.food_inspectorate_website_project.payload.request.LoginRequest;
import com.example.food_inspectorate_website_project.payload.response.JwtResponse;
import com.example.food_inspectorate_website_project.repository.RoleRepository;
import com.example.food_inspectorate_website_project.security.JwtUtils;
import com.example.food_inspectorate_website_project.service.implementation.UserDetailsImpl;
import com.example.food_inspectorate_website_project.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImpl userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        FoodInspectorateWebsiteProjectApplication.userDetails = userDetails;
        String role = userDetails.getAuthorities().toString();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getFirstName(), userDetails.getLastName(),  role));
    }
}
