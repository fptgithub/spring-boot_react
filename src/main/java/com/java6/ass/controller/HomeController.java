package com.java6.ass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java6.ass.servic.ProductServic;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	ProductServic productServic;
	
	@GetMapping("")
	public String index(Model model,
			@RequestParam(name = "page",required = false) Optional<Integer> pageNumber) {
		model.addAttribute("products", productServic.getAllProduct(pageNumber,6));
		return "home/home";
	}
}
