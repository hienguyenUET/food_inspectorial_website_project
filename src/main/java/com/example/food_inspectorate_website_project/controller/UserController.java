package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.UserDTO;
import com.example.food_inspectorate_website_project.entity.User;
import com.example.food_inspectorate_website_project.entity.Role;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.service.service.UserService;
import com.example.food_inspectorate_website_project.service.service.RoleService;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
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
}
