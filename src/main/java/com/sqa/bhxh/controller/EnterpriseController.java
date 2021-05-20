package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.helper.ApiResponse;
import com.sqa.bhxh.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("enterprise")
@CrossOrigin(origins= {"*"})
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse> getAllSocialInsurance() {
        ApiResponse object = new ApiResponse();
        List<Enterprise> enterpriseList = enterpriseService.getAll();
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(enterpriseList);
        object.setPageSize(enterpriseList.size());
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createEnterprise(
            @RequestBody Enterprise enterprise
    ) {
        ApiResponse object = new ApiResponse();
        if (enterpriseService.create(enterprise) != null) {
            object.setCode(201);
        } else {
            object.setCode(400);
        }
        object.setErrors(null);
        object.setStatus(true);
        object.setData(enterpriseService.create(enterprise));
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PatchMapping("update")
    public ResponseEntity<ApiResponse> updateEnterprise(
            @RequestBody Enterprise enterprise
    ) throws Exception {
        ApiResponse object = new ApiResponse();
        if(enterpriseService.update(enterprise) != null) {
            object.setCode(200);
        } else {
            object.setCode(404);
        }
        object.setErrors(null);
        object.setStatus(true);
        object.setData(enterpriseService.update(enterprise));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
