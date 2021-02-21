package com.algawoks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ApiRetirementHandler extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().startsWith("/v1/")) {
			response.setStatus(HttpStatus.GONE.value());
//			Encerra a execução do método neste ponto
			return false;
		}
		return true;
	}
}
