
package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.UserDTO;
import com.example.food_inspectorate_website_project.entity.User;
import com.example.food_inspectorate_website_project.entity.Role;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.payload.request.AreaAssignmentRequest;
import com.example.food_inspectorate_website_project.service.service.UserService;
import com.example.food_inspectorate_website_project.service.service.RoleService;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SubDistrictService subDistrictService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody UserDTO form) {
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        Role role = roleService.findByRoleName(form.getRole());
        SubDistrict subDistrict = subDistrictService.getSubDistrict(form.getLocation());
        user.setSubDistrict(subDistrict);
        user.setRole(role);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetails> getInformation() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("get/specialist")
    public ResponseEntity<List<User>> getUser() {
        Role role = roleService.findByRoleName("SPECIALIST");
        List<User> specialist = userService.findByRole(role);
        if (specialist != null) {
            return new ResponseEntity<>(specialist, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/assign",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> assignArea(@RequestBody AreaAssignmentRequest request) {
        System.out.println(request.getId());
        System.out.println(request.getSubDistrictName());
        User user = userService.findById(request.getId());
        System.out.println(user);
        if (user != null) {
            SubDistrict subDistrict = subDistrictService.findBySubDistrictName(request.getSubDistrictName());
            if (subDistrict != null) {
                user.setSubDistrict(null);
                user.setSubDistrict(subDistrict);
                userService.save(user);
                User user1 = userService.findById(request.getId());
                System.out.println(user1);
            } else {
                throw new RuntimeException("SubDistrict not found in database");
            }
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
