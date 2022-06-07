
package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.FoodInspectorateWebsiteProjectApplication;
import com.example.food_inspectorate_website_project.dto.UserDTO;
import com.example.food_inspectorate_website_project.entity.user.User;
import com.example.food_inspectorate_website_project.entity.user.Role;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.payload.request.AreaAssignmentRequest;
import com.example.food_inspectorate_website_project.service.service.user.UserService;
import com.example.food_inspectorate_website_project.service.service.user.RoleService;
import com.example.food_inspectorate_website_project.service.service.address.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   private final User user = FoodInspectorateWebsiteProjectApplication.user;


    @GetMapping("/get")
    public ResponseEntity<User> getInformation() {
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }
}
