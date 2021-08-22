package com.java6.ass.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java6.ass.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("select o from Order as o where o.account.username = ?1")
	public List<Order> findAllByaccount(String username); 
}
