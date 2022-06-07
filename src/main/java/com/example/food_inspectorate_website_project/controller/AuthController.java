package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.FoodInspectorateWebsiteProjectApplication;
import com.example.food_inspectorate_website_project.entity.food.Food;
import com.example.food_inspectorate_website_project.entity.user.User;
import com.example.food_inspectorate_website_project.payload.request.LoginRequest;
import com.example.food_inspectorate_website_project.payload.response.JwtResponse;
import com.example.food_inspectorate_website_project.repository.RoleRepository;
import com.example.food_inspectorate_website_project.security.JwtUtils;
import com.example.food_inspectorate_website_project.service.implementation.user.UserDetailsImpl;
import com.example.food_inspectorate_website_project.service.implementation.user.UserServiceImpl;
import com.example.food_inspectorate_website_project.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        FoodInspectorateWebsiteProjectApplication.user = new User();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        FoodInspectorateWebsiteProjectApplication.userDetails = userDetails;
        String role = userDetails.getAuthorities().toString();
        setUserInformation(FoodInspectorateWebsiteProjectApplication.userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getFirstName(), userDetails.getLastName(),  role));
    }

    private void setUserInformation(UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        FoodInspectorateWebsiteProjectApplication.user.setUsername(user.getUsername());
        FoodInspectorateWebsiteProjectApplication.user.setFirstName(user.getFirstName());
        FoodInspectorateWebsiteProjectApplication.user.setLastName(user.getLastName());
        FoodInspectorateWebsiteProjectApplication.user.setSubDistrict(user.getSubDistrict());
        FoodInspectorateWebsiteProjectApplication.user.setRole(user.getRole());
        FoodInspectorateWebsiteProjectApplication.user.setPassword(user.getPassword());
//        System.out.println(FoodInspectorateWebsiteProjectApplication.user);
    }

    @GetMapping("/logout")
    public void logout() {
        FoodInspectorateWebsiteProjectApplication.user = null;
        FoodInspectorateWebsiteProjectApplication.userDetails = null;
        System.out.println("LOGOUT!");
    }
}
