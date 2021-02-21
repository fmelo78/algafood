package com.algawoks.algafood.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {
	
	public Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
	public Long getUserId() {
		var token = (Jwt) getAuthentication().getPrincipal();
		Long userId = token.getClaim("user_id");
		return userId;
	}
	
	public String getUserName() {
		var token = (Jwt) getAuthentication().getPrincipal();
		String userName = token.getClaim("user_name");
		return userName;
	}
	
//	Pessoalmente, eu substituiria a classe getAuthentication por essa...
//	public Jwt getToken() {
//		var auth = SecurityContextHolder.getContext().getAuthentication();
//		var token = (Jwt) auth.getPrincipal();
//		return token;
//	}
}
