package com.java6.ass.jwt;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{

	String jwt;
	
	public AuthenticationResponse(String jwt) {
		// TODO Auto-generated constructor stub
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}
}
