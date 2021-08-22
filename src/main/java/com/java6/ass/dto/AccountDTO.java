package com.java6.ass.dto;

import java.util.Collection;
import java.util.List;

import com.java6.ass.entity.Authority;
import com.java6.ass.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
	
	
	
	public AccountDTO(String username, String password, String fullname, String email, String photo) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.photo = photo;
	}
	String username;
	String password;
	String fullname;
	String email;
	String photo;
	List<Authority> authorities;
}
