package com.example.food_inspectorate_website_project.controller;

import com.example.food_inspectorate_website_project.dto.inspection.FoodDTO;
import com.example.food_inspectorate_website_project.dto.inspection.InspectedProcessDTO;
import com.example.food_inspectorate_website_project.dto.inspection.InspectedStoreDTO;
import com.example.food_inspectorate_website_project.dto.inspection.StoreFoodDTO;
import com.example.food_inspectorate_website_project.entity.StoreFoodList;
import com.example.food_inspectorate_website_project.entity.food.Qualify;
import com.example.food_inspectorate_website_project.entity.food.Status;
import com.example.food_inspectorate_website_project.entity.store.Store;
import com.example.food_inspectorate_website_project.payload.request.QualifiedFood;
import com.example.food_inspectorate_website_project.payload.request.QualifiedStore;
import com.example.food_inspectorate_website_project.payload.request.Request;
import com.example.food_inspectorate_website_project.payload.response.InspectedFoodResult;
import com.example.food_inspectorate_website_project.repository.QualifyRepository;
import com.example.food_inspectorate_website_project.service.service.StoreFoodListService;
import com.example.food_inspectorate_website_project.service.service.food.QualifyService;
import com.example.food_inspectorate_website_project.service.service.food.StatusService;
import com.example.food_inspectorate_website_project.service.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inspection")
public class InspectionController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private StoreFoodListService storeFoodListService;
    @Autowired
    private QualifyService qualifyService;
    @Autowired
    private QualifyRepository qualify;
    private String regNo;

    // get store status that need to be inspected
    @PostMapping("/store")
    public void inspectedStore(@RequestBody Request request) {
        this.regNo = request.getRegNo();
    }

    @GetMapping("/store/current")
    public ResponseEntity<InspectedStoreDTO> getCurrentInspectedStore() {
        if (regNo != null) {
            Store store = storeService.findByRegNo(regNo);
            InspectedStoreDTO storeDTO = new InspectedStoreDTO();
            storeDTO.setId(store.getId());
            storeDTO.setStatus(store.getStatus().getStatus());
            storeDTO.setInspectedDate(store.getInspectedDate());
            storeDTO.setRegNo(store.getRegNo());
            storeDTO.setQualify(store.isQualify());
            storeDTO.setReason(store.getReason());
            return new ResponseEntity<>(storeDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("Register number is null, please try again");
        }
    }

    // set inspected date for store and set is inspected attribute for it
    @PutMapping("/inspected/no")
    private void setInspectedStore(@RequestBody InspectedProcessDTO processDTO) {
        Store store = storeService.findById(processDTO.getStoreId());
        if (store.getStatus().getStatus().equals("NO")) {
            Status status = statusService.findById(3);
            store.setStatus(status);
            Date date = Date.valueOf(LocalDate.now());
            store.setInspectedDate(date);
            storeService.save(store);
        }
    }

    @PutMapping("/inspected/pending")
    private void setPendingStore(@RequestBody InspectedProcessDTO processDTO) {
        Store store = storeService.findById(processDTO.getStoreId());
        if (store.getStatus().getStatus().equals("PENDING")) {
            Status status = statusService.findById(1);
            store.setStatus(status);
            storeService.save(store);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<List<Qualify>> test() {
        List<Qualify> qualifies = qualify.findAll();
        return new ResponseEntity<>(qualifies, HttpStatus.OK);
    }

    @PutMapping("/finish")
    private void removeCurrentStore() {
        this.regNo = null;
    }

    // set qualified food if it passed the inspection
    @PutMapping("/qualified/{food_id}/{store_id}")
    public void setQualifiedFood(@PathVariable int food_id, @PathVariable int store_id, @RequestBody QualifiedFood qualifiedFood) {
        StoreFoodList storeFoodList = storeFoodListService.findByFoodIdAndStoreId(food_id, store_id);
        Qualify qualify = qualifyService.findByQualify(qualifiedFood.isQualified());
        storeFoodList.setQualify(qualify);
        storeFoodList.setReason(qualifiedFood.getReason());
        storeFoodList.setInspected(true);
        storeFoodListService.save(storeFoodList);

    }

    @GetMapping("/sample/{regNo}")
    public ResponseEntity<StoreFoodDTO> getFoodList(@PathVariable String regNo) {
        Store store = storeService.findByRegNo(regNo);
        int storeId = store.getId();
        List<StoreFoodList> storeFoodLists = storeFoodListService.findByStoreId(storeId);
        StoreFoodDTO storeFoodDTOS = new StoreFoodDTO();
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for (StoreFoodList s : storeFoodLists) {
            if (!s.isInspected()) {
                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setId(s.getFood().getId());
                foodDTO.setName(s.getFood().getName());
                foodDTO.setStatus(s.getStatus().getStatus());
                foodDTO.setInspected(s.isInspected());
                foodDTO.setQualified(s.getQualify().isQualified());
                foodDTO.setReason(s.getReason());
                foodDTOS.add(foodDTO);
            }

        }
        storeFoodDTOS.setStoreName(store.getName());
        storeFoodDTOS.setFood(foodDTOS);
        return new ResponseEntity<>(storeFoodDTOS, HttpStatus.OK);
    }


    @PutMapping("/pending/{food_id}/{store_id}")
    public void setPendingStatus(@PathVariable int food_id, @PathVariable int store_id) {
        StoreFoodList storeFoodList = storeFoodListService.findByFoodIdAndStoreId(food_id, store_id);
        if (storeFoodList.getStatus().getStatus().equals("NO")) {
            Status status = statusService.findById(3);
            storeFoodList.setStatus(status);
            storeFoodList.setDateForInspection(Date.valueOf(LocalDate.now()));
            storeFoodListService.save(storeFoodList);
        } else {
            throw new RuntimeException("This sample maybe in inspected progress");
        }
    }

    @PutMapping("/yes/{food_id}/{store_id}")
    public void setYesStatus(@PathVariable int food_id, @PathVariable int store_id) {
        StoreFoodList storeFoodList = storeFoodListService.findByFoodIdAndStoreId(food_id, store_id);
        if (storeFoodList.getStatus().getStatus().equals("PENDING")) {
            Status status = statusService.findById(1);
            storeFoodList.setStatus(status);
            storeFoodListService.save(storeFoodList);
        } else {
            throw new RuntimeException("This sample maybe inspected");
        }
    }


    @GetMapping("/inspected/food")
    public ResponseEntity<List<FoodDTO>> getInspectedFood() {
        Store store = storeService.findByRegNo(regNo);
        List<StoreFoodList> storeFoodList = storeFoodListService.findByStoreId(store.getId());
        List<FoodDTO> inspectedFoodList = new ArrayList<>();
        for (StoreFoodList storeFoodList1 : storeFoodList) {
          if (storeFoodList1.isInspected()) {
              FoodDTO foodDTO = new FoodDTO();
              foodDTO.setName(storeFoodList1.getFood().getName());
              foodDTO.setId(storeFoodList1.getId());
              foodDTO.setStatus(storeFoodList1.getStatus().getStatus());
              foodDTO.setQualified(storeFoodList1.getQualify().isQualified());
              foodDTO.setInspected(storeFoodList1.isInspected());
              foodDTO.setReason(storeFoodList1.getReason());
              foodDTO.setInspectedDate(storeFoodList1.getDateForInspection());
              inspectedFoodList.add(foodDTO);
          }
        }
        return new ResponseEntity<>(inspectedFoodList, HttpStatus.OK);
    }

    @PutMapping("/update/qualify/status")
    public void updateStoreStatus(@RequestBody QualifiedStore qualifiedStore) {
        Store store = storeService.findByRegNo(regNo);
        store.setQualify(qualifiedStore.isQualify());
        store.setReason(qualifiedStore.getReason());
        storeService.save(store);
    }

//    @GetMapping()
}
