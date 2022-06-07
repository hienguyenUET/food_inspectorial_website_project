package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.UserDTO;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.entity.user.Role;
import com.example.food_inspectorate_website_project.entity.user.User;
import com.example.food_inspectorate_website_project.payload.request.AreaAssignmentRequest;
import com.example.food_inspectorate_website_project.service.service.address.SubDistrictService;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
import com.example.food_inspectorate_website_project.service.service.user.RoleService;
import com.example.food_inspectorate_website_project.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private SubDistrictService subDistrictService;
    @Autowired
    private RoleService roleService;
    @Autowired
    StoreService storeService;

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
        User user = userService.findById(request.getId());
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

    @GetMapping(value = "/stores/get")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.findAll();
        if (stores != null) {
            return new ResponseEntity<>(stores, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/non_inspected")
    public ResponseEntity<List<Store>> getNonInspectedStores() {
        List<Store> storeList = new ArrayList<>();
        List<Store> nonInspectedStore = storeService.findByStatusStatus("NO");
        List<Store> pendingInspectedStore = storeService.findByStatusStatus("PENDING");
        storeList.addAll(nonInspectedStore);
        storeList.addAll(pendingInspectedStore);
        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }
}
