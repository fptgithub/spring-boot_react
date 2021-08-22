package com.java6.ass.servic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java6.ass.dao.OrderDAO;
import com.java6.ass.dao.OrderDetailDAO;
import com.java6.ass.dao.ProductDAO;
import com.java6.ass.dto.OrderDetailDTO;
import com.java6.ass.entity.Order;
import com.java6.ass.entity.OrderDetail;

@Service
public class OrderDetailServic {

	@Autowired
	OrderDetailDAO orderdetailDAO;
	@Autowired
	OrderDAO oDAO;
	@Autowired
	ProductDAO pDAO;
	
	public OrderDetail dtoToOrderDetail(OrderDetailDTO dto) {
		OrderDetail od = orderdetailDAO.findById(dto.getId()).get();
		od.setProduct(pDAO.findById(dto.getProductid()).get());
		od.setPrice((double)(dto.getQuantity()*dto.getPrice()));
		od.setQuantity(dto.getQuantity());
		return od;
	}
	
	public OrderDetail dtoToNewOd(OrderDetailDTO dto,Order order) {
		return OrderDetail.builder()
				.price((double)(dto.getPrice()*dto.getQuantity()))
				.product(pDAO.findById(dto.getProductid()).get())
				.quantity(dto.getQuantity())
				.order(order)
				.build();
	}
	
	public OrderDetailDTO odToDto(OrderDetail od) {
		return OrderDetailDTO.builder()
				.price(od.getPrice())
				.quantity(od.getQuantity())
				.productid(od.getProduct().getId())
				.build();
	}
	
	public Page<OrderDetail> getAllOdByPage(Optional<Integer> pageNumber,Integer limit){
		Pageable page = PageRequest.of(pageNumber.orElse(0), limit);
		return orderdetailDAO.findAll(page);
	}
	
	public List<OrderDetail> getAllOdByOrder(Long id){
		return orderdetailDAO.findAllByOrder(id);
	}
	
	public OrderDetail createOrderDetail(OrderDetailDTO dto,Order order) {
		OrderDetail od = dtoToNewOd(dto,order);
		return orderdetailDAO.save(od);
	}
	
	public void createAllOrderDetail(List<OrderDetailDTO> dtos,Order order) {
		for(OrderDetailDTO dto:dtos) {
			 orderdetailDAO.save(dtoToNewOd(dto,order));
		}
	}
	
	public OrderDetail updateOrderDetail(OrderDetailDTO dto) {
		return orderdetailDAO.save(dtoToOrderDetail(dto));
	}
	
	public void deleteOrderDetail(Long orderDetailId) {
		orderdetailDAO.delete(orderdetailDAO.findById(orderDetailId).get());
	}
}
