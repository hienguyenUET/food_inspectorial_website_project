package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.FoodInspectorateWebsiteProjectApplication;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.entity.user.User;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// this controller is for specialists

@RestController
@RequestMapping("/specialist")
public class SpecialistController {
    @Autowired
    private StoreService storeService;

    private User user = new User();

    @GetMapping("/stores/get")
    public ResponseEntity<List<Store>> getStoreList() {
        SubDistrict subDistrict = user.getSubDistrict();
        List<Store> stores = storeService.findByAddress_SubDistrict(subDistrict);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/get/non_inspected")
    public ResponseEntity<List<Store>> getNonInspectedStores() {
        user = FoodInspectorateWebsiteProjectApplication.user;
        List<Store> storeList = new ArrayList<>();
        List<Store> nonInspectedStore = storeService.findByStatusStatusAndAddress_SubDistrict("NO", user.getSubDistrict());
        List<Store> pendingInspectedStore = storeService.findByStatusStatusAndAddress_SubDistrict("PENDING", user.getSubDistrict());
        storeList.addAll(nonInspectedStore);
        storeList.addAll(pendingInspectedStore);
        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }
}
