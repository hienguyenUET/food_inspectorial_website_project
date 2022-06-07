//package com.example.food_inspectorate_website_project.controller;
//
//import com.example.food_inspectorate_website_project.entity.store.Certification;
//import com.example.food_inspectorate_website_project.payload.request.DeleteCertificateForm;
//import com.example.food_inspectorate_website_project.service.service.store.CertificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/certificate")
//public class CertificateController {
//    @Autowired
//    private CertificationService certificationService;
//
//    @DeleteMapping("/delete")
//    public void deleteCertificate(@RequestBody DeleteCertificateForm form) {
//        Certification certification = certificationService.findByCertificationNumber(form.getCertNumber());
//        if(certification != null){
//            certificationService.deleteById(certification.getId());
//        }
//        else {
//            throw new RuntimeException("Store not found");
//        }
//    }
//}
