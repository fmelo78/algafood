package com.algawoks.algafood.api.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceUriHelper {

	public static HttpHeaders addHeaderLocation (Object resourceId) {
	String uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.pathSegment(resourceId.toString()).build().toString();
	HttpHeaders headers = new HttpHeaders();
	headers.add(HttpHeaders.LOCATION, uri);
	return headers;
	}
	
}