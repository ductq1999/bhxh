package com.sqa.bhxh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqa.bhxh.services.UserService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins= {"*"})
public class UserController {

	@Autowired
	private UserService userService;
	
	
}
