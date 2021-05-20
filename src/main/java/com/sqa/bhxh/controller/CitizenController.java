package com.sqa.bhxh.controller;

import com.sqa.bhxh.entities.Citizen;
import com.sqa.bhxh.entities.Enterprise;
import com.sqa.bhxh.helper.ApiResponse;
import com.sqa.bhxh.services.CitizenService;
import com.sqa.bhxh.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("citizen")
@CrossOrigin(origins= {"*"})
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping("get-all")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse object = new ApiResponse();
        List<Citizen> citizenList = citizenService.getAll();
        object.setCode(200);
        object.setErrors(null);
        object.setStatus(true);
        object.setPageSize(citizenList.size());
        object.setData(citizenList);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> create(
            @RequestBody Citizen citizen
    ) {
        ApiResponse object = new ApiResponse();
        if (citizenService.create(citizen) != null) {
            object.setCode(201);
        } else {
            object.setCode(400);
        }
        object.setErrors(null);
        object.setStatus(false);
        object.setData(citizenService.create(citizen));
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PatchMapping("update")
    public ResponseEntity<ApiResponse> update(
            @RequestBody Citizen citizen
    ) throws Exception {
        ApiResponse object = new ApiResponse();
        if (citizenService.update(citizen) != null) {
            object.setCode(200);
        } else {
            object.setCode(404);
        }
        object.setErrors(null);
        object.setStatus(true);
        object.setData(citizenService.update(citizen));
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
