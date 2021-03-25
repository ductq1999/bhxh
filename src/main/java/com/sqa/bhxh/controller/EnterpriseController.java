package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.entities.SocialInsurance;
import com.sqa.bhxh.helper.ApiResponse;
import com.sqa.bhxh.services.EnterpriseService;
import com.sqa.bhxh.services.SocialInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("enterprise")
@CrossOrigin(origins= {"*"})
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse> getAllSocialInsurance() {
        ApiResponse object = new ApiResponse();
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(enterpriseService.getAll());
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createEnterprise(
            @RequestBody Enterprise enterprise
    ) {
        ApiResponse object = new ApiResponse();
        object.setCode(201);
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
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(enterpriseService.update(enterprise));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
