package com.algawoks.algafood.core.web;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private ApiDeprecationHandler apiDeprecationHandler;

//	@Autowired
//	private ApiRetirementHandler apiRetirementHandler;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/restaurantes/**")
//			.allowedMethods("*")
//			.allowedOrigins("http://www.algafood.local:8000")
//			.maxAge(30);
//		registry.addMapping("/cozinhas/**")
//			.allowedMethods("*")
//			.allowedOrigins("http://www.matafome.local:8000")
//			.maxAge(30);
		registry.addMapping("/**")
			.allowedMethods("*")
			.allowedOrigins("*")
			.maxAge(30);
	}
	
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
//	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiDeprecationHandler);
	}
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
}


