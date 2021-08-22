package com.java6.ass.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java6.ass.dao.CategoryDAO;
import com.java6.ass.dto.CategoryDTO;
import com.java6.ass.entity.Category;
import com.java6.ass.servic.CategoryServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/category")

public class AdminCategoryRestController {

	@Autowired
	CategoryServic cServic;
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@GetMapping("/table")
	public ResponseEntity<Page<Category>> table(@RequestParam("page") Optional<Integer> page){
		return ResponseEntity.ok(cServic.getAllCategoryByPage(page,12));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@PutMapping("/create")
	public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto){
		return ResponseEntity.ok(cServic.cateToDto(cServic.createCate(dto)));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@PostMapping("/update")
	public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto){
		return ResponseEntity.ok(cServic.cateToDto(cServic.updateCategory(dto)));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@DeleteMapping("/delete/{categoryid}")
	public String delete(@PathVariable("categoryid") String id) {
		cServic.deleteCategory(id);
		return "ok";
	}
}
