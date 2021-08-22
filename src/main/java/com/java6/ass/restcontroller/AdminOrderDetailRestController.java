package com.java6.ass.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java6.ass.dto.OrderDetailDTO;
import com.java6.ass.entity.OrderDetail;
import com.java6.ass.servic.OrderDetailServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/api/orderdetail")

public class AdminOrderDetailRestController {

	@Autowired
	OrderDetailServic odServic;	
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@GetMapping("/table")
	public ResponseEntity<Page<OrderDetail>> table(@RequestParam("page") Optional<Integer> page){
		return ResponseEntity.ok(odServic.getAllOdByPage(page, 12));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@DeleteMapping("/delete/{orderdetaiid}")
	public String delete(@PathVariable("orderdetaiid") Long orderdetaiid) {
		odServic.deleteOrderDetail(orderdetaiid);
		return "ok";
	}
}
