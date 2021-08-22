package com.java6.ass.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java6.ass.entity.Category;
import com.java6.ass.servic.CategoryServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/card")
public class CardController {

	@Autowired
	CategoryServic cateServic;
		
	@GetMapping("/categorys")
	public ResponseEntity<List<Category>> categorys() {
		return ResponseEntity.ok(cateServic.getAllCategory());
	}
	
}
