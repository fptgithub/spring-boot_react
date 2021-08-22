package com.java6.ass.servic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java6.ass.dao.AccountDAO;
import com.java6.ass.dao.RoleDAO;
import com.java6.ass.dto.AccountDTO;
import com.java6.ass.entity.Account;
import com.java6.ass.entity.Authority;
import com.java6.ass.entity.Role;

@Service
public class AccountServic {

	@Autowired
	AccountDAO accountDAO;
	@Autowired
	AuthorityServic authServic;
	@Autowired
	RoleDAO roleDAO;
	
	public Optional<Account> getByUsername(String username) {
		return accountDAO.findById(username);
	}
	
	public Page<AccountDTO> getAllAccountDtoByPage(Optional<Integer> pageNumber,Integer limit){
		Pageable page = PageRequest.of(pageNumber.orElse(0), limit);
		Page<AccountDTO> rs = accountDAO.findAllAccountDto(page);
		for(AccountDTO a:rs.getContent()) {
			a.setAuthorities(authServic.getAllAuByAccount(a.getUsername()));
		}
		return rs;
	}
	
	public Page<Account> getAllAccountByPage(Optional<Integer> pageNumber,Integer limit){
		Pageable page = PageRequest.of(pageNumber.orElse(0), limit);
		return accountDAO.findAll(page);
	}
	
	public Account createAccount(AccountDTO dto) {
		Account account = dtoToNewAccount(dto);
		account = accountDAO.save(account);
		authServic.createAuthority(account, roleDAO.findById("CUST").get());
		return account;
	}
	
	public Account updateAccount(AccountDTO accountDTO) {
		return accountDAO.save(dtoToAccount(accountDTO));
	}
	
	public void deleteAccount(String username) {
		accountDAO.delete(accountDAO.findById(username).get());
	}
	
	public Account dtoToAccount(AccountDTO dto) {
		Account account = accountDAO.findById(dto.getUsername()).get(); 
		account.setEmail(dto.getEmail());
		account.setFullname(dto.getFullname());
		account.setPassword(dto.getPassword());
		account.setPhoto(dto.getPhoto());
		return account;
	}
	
	public Account dtoToNewAccount(AccountDTO dto) {
		return Account.builder()
				.email(dto.getEmail())
				.fullname(dto.getFullname())
				.password(dto.getPassword())
				.photo(dto.getPhoto())
				.build();
	}
	
	public AccountDTO accountToDto(Account account) {
		return AccountDTO.builder()
				.email(account.getEmail())
				.fullname(account.getFullname())
				.password(account.getPassword())
				.photo(account.getPhoto())
				.build();
	}
}
