package com.algawoks.algafood.core.squiggly;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {

	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
//		Argumento "campos" refere-se ao nome do parâmetro que deverá ser inserido na requisição HTTP
		Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos", null));
		
//		Filtros atuarão apenas para as URLS definidas com esse padrão.
//		Removendo essa linha, os filtros atuarão em todas as URLs
		var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
		
//		Sempre que uma requisição chegar à API, passará por esse filtro
		var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();
		filterRegistration.setFilter(new SquigglyRequestFilter());
		filterRegistration.setOrder(1);
		filterRegistration.setUrlPatterns(urlPatterns);
		
		return filterRegistration;
	}
	
}
