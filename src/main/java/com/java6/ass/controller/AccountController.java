package com.java6.ass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AccountController {

	@GetMapping("/home/login")
	public String loginform() {
		return "home/login";
	}
	
}
