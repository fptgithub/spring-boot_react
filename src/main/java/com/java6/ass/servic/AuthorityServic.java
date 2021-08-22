package com.java6.ass.servic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java6.ass.dao.AccountDAO;
import com.java6.ass.dao.AuthorityDAO;
import com.java6.ass.entity.Account;
import com.java6.ass.entity.Authority;
import com.java6.ass.entity.Role;

@Service
public class AuthorityServic {

	@Autowired
	AuthorityDAO authDAO;
	@Autowired
	AccountDAO accountDAO;
	
	public Authority createAuthority(Account account,Role role) {
		return authDAO.save(Authority.builder().account(account).role(role).build());
	}
	
	public List<Authority> getAllAuByAccount(String username){
		return authDAO.findAllByAccount(username);
	}
	
	public void updateAuthority(Account account,List<Role> roles) {
		for(Authority auth:accountDAO.findById(account.getUsername()).get().getAuthorities()) {
			authDAO.delete(auth);
		}
		for(Role role:roles) {
			authDAO.save(Authority.builder().account(account).role(role).build());
		}
	}
}
