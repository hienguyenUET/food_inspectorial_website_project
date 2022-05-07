package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.PremiseDTO;
import com.example.food_inspectorate_website_project.entity.Premise;
import com.example.food_inspectorate_website_project.entity.address.Address;
import com.example.food_inspectorate_website_project.service.service.BusinessTypeService;
import com.example.food_inspectorate_website_project.service.service.PremiseService;
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
@RequestMapping("/premise")
public class PremiseController {
    @Autowired
    private PremiseService premiseService;
    @Autowired
    private SubDistrictService subDistrictService;
    @Autowired
    private BusinessTypeService businessTypeService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Premise> addPremise(@RequestBody PremiseDTO form) {
        Premise premise = new Premise();
        premise.setName(form.getName());
        premise.setPhoneNumber(form.getPhoneNumber());
        premise.setRegNo(form.getRegNo());
        premise.setBusinessType(businessTypeService.getTypeOfBusiness(form.getBusinessType()));
        Address address = new Address();
        address.setNumber(form.getAddress().getNumber());
        address.setAlley(form.getAddress().getAlley());
        address.setStreet(form.getAddress().getStreet());
        address.setSubDistrict(subDistrictService.getSubDistrict(form.getAddress().getSubDistrict()));
        premise.setAddress(address);
        premiseService.save(premise);
        return new ResponseEntity<>(premise, HttpStatus.OK);
    }
}
