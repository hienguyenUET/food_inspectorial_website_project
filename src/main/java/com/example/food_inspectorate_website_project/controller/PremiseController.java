package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.PremiseDTO;
import com.example.food_inspectorate_website_project.entity.Store;
import com.example.food_inspectorate_website_project.entity.address.Address;
import com.example.food_inspectorate_website_project.service.service.BusinessTypeService;
import com.example.food_inspectorate_website_project.service.service.StoreService;
import com.example.food_inspectorate_website_project.service.service.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class PremiseController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private SubDistrictService subDistrictService;
    @Autowired
    private BusinessTypeService businessTypeService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> addPremise(@RequestBody PremiseDTO form) {
        Store store = new Store();
        store.setName(form.getName());
        store.setPhoneNumber(form.getPhoneNumber());
        store.setRegNo(form.getRegNo());
        store.setBusinessType(businessTypeService.getTypeOfBusiness(form.getBusinessType()));
        Address address = new Address();
        address.setNumber(form.getAddress().getNumber());
        address.setAlley(form.getAddress().getAlley());
        address.setStreet(form.getAddress().getStreet());
        address.setSubDistrict(subDistrictService.getSubDistrict(form.getAddress().getSubDistrict()));
        store.setAddress(address);
        storeService.save(store);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
