package com.java6.ass.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java6.ass.dto.AccountDTO;
import com.java6.ass.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{

	@Query("select a from Account as a")
	public Page<Account> findAllAccount(Pageable page);
	
	@Query("select new com.java6.ass.dto.AccountDTO(a.username,a.password,a.fullname,a.email,a.photo)"
			+ " from Account as a")
	public Page<AccountDTO> findAllAccountDto(Pageable page);
}
