package com.java6.ass.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.java6.ass.utils.MyUserdetailServic;

@Component
public class JwtFilter extends OncePerRequestFilter{
	@Autowired
    private MyUserdetailServic userDetailsService;

    @Autowired
    private TokenUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	String authorizationHeader = null;
    	if(request.getHeader("Cookie")!=null) {
    	for(String s:request.getHeader("Cookie").split(";")) {
    		if(s.strip().startsWith("jwt")) {
    			authorizationHeader = s.replace("jwt=", "").strip();
    		}
    	}
    	}
        System.out.println("header : "+request.getHeader("Cookie"));
//        if(authorizationHeader == null) {
//        		System.out.println();
//		        for(Cookie c:request.getCookies()) {
//		        	if(request.getHeader("Cookie")!=null) { 
//		        		authorizationHeader =request.getHeader("Cookie").replace("jwt=", "");
//		        		System.out.println(request.getHeader("Cookie"));}
//		        }
//		        System.out.println();
//        }
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
