package com.java6.ass.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieUtils {

	@Autowired
	HttpServletRequest rs;
	@Autowired
	HttpServletResponse rp;
	
	public void createCookie(String name,String value) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(60*60*24);
		rp.addCookie(c);
	}
	
	public void session(String name,String value) {
		rs.getSession().setAttribute(name, value);
	}
	
	public String getsessionval(String name) {
		return rs.getSession().getAttribute(name)!=null?(String) rs.getSession().getAttribute(name):null;
	}
	
	public void updateCookie(String name,String value) {
		for(Cookie c: rs.getCookies()) {
			if(c.getName().equals(name)) {
				c.setValue(value);
				break;
			}
		}
	}
	
	public Cookie getCookie(String name) {
		for(Cookie c: rs.getCookies()) {
			if(c.getName().equals(name))return c;
		}
		return null;
	}
	
}
