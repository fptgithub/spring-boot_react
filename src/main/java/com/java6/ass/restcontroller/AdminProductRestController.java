package com.java6.ass.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

import com.java6.ass.dto.ProductDTO;
import com.java6.ass.entity.Product;
import com.java6.ass.servic.ProductServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/api/product")

public class AdminProductRestController {
	
	@Autowired
	ProductServic pServic;
	
	@PreAuthorize("hasAuthority('ROLE_STAF') or hasAuthority('ROLE_DIRE')")
	@GetMapping("/table")
	public ResponseEntity<Page<Product>> table(@RequestParam("page") Optional<Integer> page,Authentication au){
		System.out.println(au.getPrincipal());
		return ResponseEntity.ok(pServic.getAllProduct(page, 12));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@PostMapping("/update")
	public ResponseEntity<ProductDTO> update(@RequestBody Optional<ProductDTO> dto) {
		return ResponseEntity.ok(pServic.proToDto(pServic.updateProduct(dto.get())));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<ProductDTO> findOne(@PathVariable("id") Integer id){
		return ResponseEntity.ok(pServic.proToDto(pServic.getById(id)));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@PutMapping("/create")
	public ResponseEntity<ProductDTO> create(@RequestBody Optional<ProductDTO> dto) {
		return ResponseEntity.ok(pServic.proToDto(pServic.createProduct(dto.get())));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@DeleteMapping("/delete/{productid}")
	public String delete(@PathVariable("productid") Integer id) {
		pServic.deleteProduct(id);
		return "ok";
	}
}
