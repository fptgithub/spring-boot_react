package com.java6.ass.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java6.ass.entity.Account;
import com.java6.ass.entity.Authority;
import com.java6.ass.servic.AccountServic;

@Service
public class MyUserdetailServic implements UserDetailsService{

	@Autowired
	AccountServic accountServic;
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountServic.getByUsername(username).orElseThrow(()->{throw new UsernameNotFoundException("not found");});
		String role[] = account.getAuthorities().stream().map(a -> a.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]);
		return User.builder().username(account.getUsername())
				.password(encoder.encode(account.getPassword()))
				.roles(role)
				.build();
	}
}
