package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.PremiseDTO;
import com.example.food_inspectorate_website_project.entity.store.Certification;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.entity.address.Address;
import com.example.food_inspectorate_website_project.payload.request.DeleteCertificateForm;
import com.example.food_inspectorate_website_project.payload.response.CertificationForm;
import com.example.food_inspectorate_website_project.service.service.store.BusinessTypeService;
import com.example.food_inspectorate_website_project.service.service.store.CertificationService;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
import com.example.food_inspectorate_website_project.service.service.address.SubDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO: Hiep fix loi null certification o UI

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private SubDistrictService subDistrictService;
    @Autowired
    private BusinessTypeService businessTypeService;
    @Autowired
    private CertificationService certificationService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> addStore(@RequestBody PremiseDTO form) {
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


    // goi y co so chua co giay chung nhan/ giay chung nhan da het han su dung
//    @GetMapping("/get/unverified")
//    public ResponseEntity<List<Store>> getUnverifiedStores() {
//        List<Store> unVerifiedStore = new ArrayList<>();
//        List<Store> nullCertificateStore = storeService.findByCertificationNull();
//        Date date = Date.valueOf(LocalDate.now());
//        List<Certification> expiredCertificateStore = certificationService.findByExpirationDateBefore(date);
//        for (Certification cert : expiredCertificateStore) {
//            unVerifiedStore.add(cert.getStore());
//        }
//        unVerifiedStore.addAll(nullCertificateStore);
//        return new ResponseEntity<>(unVerifiedStore, HttpStatus.OK);
//    }

    // get non-inspected stores


    @PutMapping(value = "/add/certificate") // duong link de them cert
    public void addCertification(@RequestBody CertificationForm certificationForm) {
        Certification cert = new Certification();
        cert.setCertificationNumber(certificationForm.getCertNumber());
        cert.setStartDate(certificationForm.getStartDate());
        cert.setExpirationDate(certificationForm.getExpirationDate());
        Store store = storeService.findByRegNo(certificationForm.getRegNumber());
        if (store != null) {
//            certificationService.save(cert);
            if (store.getCertification() != null) {
                Certification certification = store.getCertification();
                store.setCertification(null);
                certificationService.deleteById(certification.getId());
            }
            store.setCertification(cert);
            storeService.save(store);
        } else {
            throw new RuntimeException("Store not found");
        }
    }

    @DeleteMapping("certificate/delete")
    public void deleteCertificate(@RequestBody DeleteCertificateForm certificationForm) {
        Store store = storeService.findByRegNo(certificationForm.getRegNumber());
        if (store != null) {
            Certification certification = store.getCertification();
            store.setCertification(null);
            storeService.save(store);
            if (certification != null) {
                certificationService.deleteById(certification.getId());
            } else {
                throw new RuntimeException("Certification not found");
            }
        } else {
            throw new RuntimeException("Server return response: " + HttpStatus.NOT_FOUND.value());
        }
    }
}
