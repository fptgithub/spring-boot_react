package com.java6.ass.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java6.ass.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("select p from Product as p where p.category.id = ?1")
	public Page<Product> findAllByCategory(String categoryId,Pageable page);
	
	public Page<Product> findByNameContaining(String name,Pageable page);
	
	@Query("select p from Product as p where p.price between ?1 and ?2")
	public Page<Product> findByPrice(Double price1,Double price2,Pageable page);
}
