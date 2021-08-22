package com.java6.ass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java6.ass.entity.Product;
import com.java6.ass.servic.ProductServic;

@Controller
@RequestMapping("/home")
public class ProductController {

	@Autowired
	ProductServic productServic;
	
	@GetMapping("/{categoryId}")
	public String productByCategory(@PathVariable("categoryId") String id,Model model,
			@RequestParam(name = "page",required = false) Optional<Integer> pageNumber) {
		model.addAttribute("products", productServic.getAllProductByCategory(id,pageNumber,6));
		model.addAttribute("categoryId", id);
		return "home/home";
	}
	
	@GetMapping("/detail/{productId}")
	public String productDetail(@PathVariable("productId") Integer id,Model model,
			@RequestParam(name = "page",required = false) Optional<Integer> pageNumber) {
		Product p = productServic.getById(id);
		model.addAttribute("product", p);
		model.addAttribute("products", productServic.getAllProductByCategory(p.getCategory().getId(),pageNumber,4));
		return "home/product";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name = "name",required = false) Optional<String> name,
			@RequestParam(name = "price1" ,required = false) Optional<Double> price1,
			@RequestParam(name = "price2" ,required = false) Optional<Double> price2,
			@RequestParam(name = "page",required = false) Optional<Integer> pageNumber,
			Model model
			) {
		Page<Product> page = null;
		if(name.isPresent()) {
			page = productServic.searchWithName(name.get(), pageNumber);
		}else if(price1.isPresent() && price2.isPresent()) {
			page = productServic.searchhWithPrice(price1.get(), price2.get(), pageNumber);
		}
		model.addAttribute("products", page);
		return "home/home";
	}
}
