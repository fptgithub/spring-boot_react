package com.java6.ass.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java6.ass.entity.Authority;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

	@Query("select au from Authority as au where au.account.username = ?1")
	public List<Authority> findAllByAccount(String username);
	
}
