package com.sqa.bhxh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.bhxh.helper.ApiResponse;
import com.sqa.bhxh.services.UserService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins= {"*"})
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("test")
	public ResponseEntity<ApiResponse> test() {
		ApiResponse object = new ApiResponse();
		object.setCode(200);
		object.setErrors(null);
		object.setStatus(true);
		object.setData("success");
		return new ResponseEntity<ApiResponse>(object, HttpStatus.OK);
	}
	
}
