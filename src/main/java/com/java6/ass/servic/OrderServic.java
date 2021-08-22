package com.java6.ass.servic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java6.ass.dao.AccountDAO;
import com.java6.ass.dao.OrderDAO;
import com.java6.ass.dto.OrderDTO;
import com.java6.ass.dto.OrderDetailDTO;
import com.java6.ass.entity.Order;
import com.java6.ass.entity.OrderDetail;
import com.java6.ass.utils.OrderState;

@Service
public class OrderServic {

	@Autowired
	OrderDAO orderDAO;
	@Autowired 
	AccountDAO aDAO;
	@Autowired
	OrderDetailServic odSetvic;
	
	public Page<Order> getAllOByPage(Optional<Integer> pageNumber,Integer limit){
		Pageable page = PageRequest.of(pageNumber.orElse(0), limit);
		return orderDAO.findAll(page);
	}
	
	public Order dtoToNewOrder(OrderDTO dto,String username) {
		return Order.builder()
				.address(dto.getAddress())
				.createDate(new Date())
				.account(aDAO.findById(username).get())
				.build();
	}
	
	public Order createOrder(OrderDTO dto,String username) {
		Order order = orderDAO.save(dtoToNewOrder(dto, username));
		odSetvic.createAllOrderDetail(jsonToListOrderdetail(dto.getListproduct()),order);
		return orderDAO.findById(order.getId()).get();
	}
	
	public List<OrderDetailDTO> jsonToListOrderdetail(String json){
		ObjectMapper obj = new ObjectMapper();
		try {
			return obj.readValue(json, new TypeReference<List<OrderDetailDTO>>(){});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		String val = "[{\"productid\":\"1081\",\"price\":\"19.0\",\"quantity\":\"1\"},{\"productid\":\"1003\",\"price\":\"10.0\",\"quantity\":4},{\"productid\":\"1002\",\"price\":\"19.0\",\"quantity\":3},{\"productid\":\"1076\",\"price\":\"18.0\",\"quantity\":4},{\"productid\":\"1005\",\"price\":\"21.35\",\"quantity\":\"4\"},{\"productid\":\"1020\",\"price\":\"81.0\",\"quantity\":\"1\",\"name\":\"Sir Rodney's Marmalade\"},{\"productid\":\"1016\",\"price\":\"17.45\",\"quantity\":\"10000\",\"name\":\"Pavlova\"}]";
//		ObjectMapper obj = new ObjectMapper();
//		try {
//			System.out.println(obj.readValue(val, new TypeReference<List<OrderDetailDTO>>(){}));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public List<Order> getorderbyuser(String username){
		return orderDAO.findAllByaccount(username);
	}
	
	public void deleteOrder(Long id) {
		orderDAO.delete(orderDAO.findById(id).get());
	}
}
