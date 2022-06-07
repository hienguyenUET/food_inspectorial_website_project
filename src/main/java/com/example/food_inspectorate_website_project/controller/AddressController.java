package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.entity.address.City;
import com.example.food_inspectorate_website_project.entity.address.District;
import com.example.food_inspectorate_website_project.entity.address.SubDistrict;
import com.example.food_inspectorate_website_project.service.service.address.CityService;
import com.example.food_inspectorate_website_project.service.service.address.DistrictService;
import com.example.food_inspectorate_website_project.service.service.address.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private CityService cityService;
    @Autowired
    private SubDistrictService subDistrictService;

    @GetMapping("/districts")
    private ResponseEntity<List<District>> getDistricts() {
        if (getDistrictList() != null) {
            return new ResponseEntity<>(getDistrictList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/subdistricts")
    private ResponseEntity<List<List<SubDistrict>>> getSubDistricts() {
        List<List<SubDistrict>> districtList = new ArrayList<>();
        if (getDistrictList() != null) {
            for (District district : getDistrictList()) {
                List<SubDistrict> subDistricts = subDistrictService.findByDistrict(district);
                districtList.add(subDistricts);
            }
            return new ResponseEntity<>(districtList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/subdistricts/{district}")
    private ResponseEntity<List<SubDistrict>> getAllSubDistrictList(@PathVariable String district) {
        List<SubDistrict> subDistricts = subDistrictService.findByDistrict_DistrictName(district);
        if (subDistricts != null) {
            return new ResponseEntity<>(subDistricts, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private List<District> getDistrictList() {
        City city = cityService.getCity("Hà Nội");
        return districtService.findByCity(city);
    }
}
