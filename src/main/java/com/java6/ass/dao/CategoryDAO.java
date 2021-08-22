package com.java6.ass.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java6.ass.entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, String>{

}
