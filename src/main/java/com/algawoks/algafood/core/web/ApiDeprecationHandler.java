package com.algawoks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ApiDeprecationHandler extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().startsWith("/v1/")) {
			response.addHeader("X-Algafood-Deprecated", "API pública para clientes e restaurantes <br>"
					+ "<strong> API depreciada e será desativada em 01/01/2021. Favor utilizar a versão atualizada </strong>");
		}
		return true;
	}
}
