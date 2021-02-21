package com.algawoks.algafood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable().cors()
			.and().oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		
//		Popula o jwtAuthenticationConverter com uma Collection<GrantedAuthority>
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			List<String> authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
//			Carrega as authorities dos usuários em formato GrantedAuthorities
			Collection<GrantedAuthority> userAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//			Carrega as authorities dos escopos em formato GrantedAuthorities
			JwtGrantedAuthoritiesConverter scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> scopeAuthorities = scopesAuthoritiesConverter.convert(jwt);
//			Faz o merge das authorities (usuários e escopos)
			Collection<GrantedAuthority> grantedAuthorities = userAuthorities;
			grantedAuthorities.addAll(scopeAuthorities);

			return grantedAuthorities;
		});
		
		return jwtAuthenticationConverter;
	}
	
	
	
//	Como alternativa, desenvolver um método que retorne uma Collection<GrantedAuthority>
//	para substituir a expressão lambda do método setJwtGrantedAuthoritiesConverter, acima
//	private Collection<GrantedAuthority> getAuthorities(){
//		Jwt jwt = algaSecurity.getToken();
//		jwt.getClaim("")
//		Collection<GrantedAuthority> auth = new ArrayList<>();
//		return auth;
//	}
	
		
	
//	Esse bean deve ser utilizado somente quando usamos chave simétrica. Caso contrário, é desnecessário
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		SecretKey secretKey = new SecretKeySpec("algaworksalgaworksalgaworksalgaworks".getBytes(), "HmacSHA256");
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
	
}
