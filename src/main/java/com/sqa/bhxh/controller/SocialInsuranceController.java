package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.SocialInsurance;
import com.sqa.bhxh.helper.ApiResponse;
import com.sqa.bhxh.services.SocialInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("social-insurance")
@CrossOrigin(origins= {"*"})
public class SocialInsuranceController {

    @Autowired
    private SocialInsuranceService socialInsuranceService;

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse> getAllSocialInsurance() {
        ApiResponse object = new ApiResponse();
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(socialInsuranceService.getAllSocialInsurances());
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createSocialInsurance(
            @RequestBody SocialInsurance socialInsurance
    ) {
        ApiResponse object = new ApiResponse();
        object.setCode(201);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(socialInsuranceService.createSocialInsurance(socialInsurance));
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PatchMapping("update")
    public ResponseEntity<ApiResponse> updateSocialInsurance(
            @RequestBody SocialInsurance socialInsurance
    ) throws Exception {
        ApiResponse object = new ApiResponse();
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setData(socialInsuranceService.updateSocialInsurance(socialInsurance));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
