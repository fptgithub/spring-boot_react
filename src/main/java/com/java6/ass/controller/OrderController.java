package com.java6.ass.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java6.ass.dto.OrderDTO;
import com.java6.ass.servic.OrderDetailServic;
import com.java6.ass.servic.OrderServic;

@Controller
@RequestMapping("/home")
public class OrderController {
	@Autowired
	OrderServic oServic;
	@Autowired
	OrderDetailServic odServic;

	@GetMapping("/order")
	public String orderform(HttpServletRequest http) {
		return "/home/order";
	}
	
	@PostMapping("/order")
	public String order(OrderDTO dto,Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		oServic.createOrder(dto, user.getUsername());
		return "redirect:/home";
	}
	
	@GetMapping("/orderhistory")
	public String orderhistory(Authentication authentication,Model model) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		model.addAttribute("orders", oServic.getorderbyuser(user.getUsername()));
		return "/home/orderhistory";
	}
	@GetMapping("/orderhistory/{orderid}")
	public String orderhistorydetail(Model model,@PathVariable("orderid") Long id) {
		model.addAttribute("orders", odServic.getAllOdByOrder(id));
		return "/home/orderhistorydetail";
	}
}
