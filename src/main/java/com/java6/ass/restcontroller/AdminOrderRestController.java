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

import com.java6.ass.entity.Order;
import com.java6.ass.entity.OrderDetail;
import com.java6.ass.servic.OrderDetailServic;
import com.java6.ass.servic.OrderServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/api/order")

public class AdminOrderRestController {
	
	@Autowired
	OrderDetailServic odServic;
	@Autowired
	OrderServic oServic;
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@GetMapping("/table")
	public ResponseEntity<Page<Order>> table(@RequestParam("page") Optional<Integer> page){
		return ResponseEntity.ok(oServic.getAllOByPage(page, 12));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@GetMapping("/orderdetail/{orderid}")
	public ResponseEntity<List<OrderDetail>> tableByOrder(@PathVariable("orderid") Long id
			){
		return ResponseEntity.ok(odServic.getAllOdByOrder(id));
	}
	@PreAuthorize("hasRole('STAF') or hasRole('DIRE')")
	@DeleteMapping("/delete/{orderid}")
	public String delete(@PathVariable("orderid") Long orderid) {
		oServic.deleteOrder(orderid);
		return "ok";
	}
}
