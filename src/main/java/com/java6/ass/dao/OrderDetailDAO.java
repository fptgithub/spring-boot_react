package com.java6.ass.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java6.ass.entity.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

	@Query("select od from OrderDetail as od where od.order.id = ?1")
	public List<OrderDetail> findAllByOrder(Long id);
	
}
